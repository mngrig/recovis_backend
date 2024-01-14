package com.recovis.SpringServer.model.eav;

import com.recovis.SpringServer.model.all_fields.AllFields;
import com.recovis.SpringServer.model.all_fields.AllFieldsRepository;
import com.recovis.SpringServer.model.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class EavDao {

    @Autowired
    private EavRepository eavRepository;

    @Autowired
    private AllFieldsRepository allFieldsRepository;

    @Autowired
    private PatientRepository patientRepository;


    public void saveAll(ArrayList<Eav> eav){
        eavRepository.saveAll(eav);
    }

    public List<Eav> getAllEAVs(){
        return (List<Eav>) eavRepository.findAll();
    }

    public long getSize(){
        return eavRepository.count();
    }

    public List<Eav> getAllExams(String patient_id, String start_date, String end_date){

        List<Eav> allEavs = getAllEAVs();
        List<Eav> patientEavs = new ArrayList<>();
        Date startDate = Date.valueOf(start_date);
        Date endDate = Date.valueOf(end_date);
        // get stored values
        for(int i = 0 ; i < allEavs.size(); i++){
            Eav entry = allEavs.get(i);
            Date entryDate = entry.getId().getExam_date();
            if(patient_id.equals(entry.getId().getPatient_id()) && entryDate.before(endDate) && entryDate.after(startDate)){
                patientEavs.add(entry);
            }
        }


        List<AllFields> allFields = new ArrayList<>();
        Streamable.of(allFieldsRepository.findAll())
                .forEach(allFields::add);


        //regex patterns
        List<Pattern> patterns = new ArrayList<>();
        patterns.add(Pattern.compile("%(\\d+)/%(\\d+)\\*(\\d+)"));
        patterns.add(Pattern.compile("%(\\d+)#>(\\d+)"));

        List<Eav> newEntries = new ArrayList<>();


        //get computed values
        for(int i = 0; i < allFields.size(); i++)
        {
            // for computed daily fields
            if(allFields.get(i).getType().equals("computed")){
                //get the expression
                String expression = allFields.get(i).getExpression();
                //handle the expression
                for (int j = 0; j < patterns.size(); j++) {
                    Matcher matcher = patterns.get(j).matcher(expression);
                    if (matcher.matches()) {
                        System.out.println("Match found for pattern " + j + " in expression: " + expression);

                        //Percentage - 1st pattern
                        if(matcher.groupCount() == 3)
                        {
                            System.out.println("Percentage");
                            int field_id_1 = Integer.parseInt(matcher.group(1));
                            int field_id_2 = Integer.parseInt(matcher.group(2));
                            int multiplier = Integer.parseInt(matcher.group(3));

                            if(allFields.get(i).getFrequency().equals("daily"))
                            {
                                for(int k = 0; k < patientEavs.size(); k++){
                                    EavID eavID_1 = new EavID(patient_id,patientEavs.get(k).getId().getExam_date(), field_id_1);
                                    EavID eavID_2 = new EavID(patient_id,patientEavs.get(k).getId().getExam_date(), field_id_2);
                                    // if they both exist then we can calculate the daily percentage
                                    if(eavRepository.findById(eavID_1).isPresent()
                                            && eavRepository.findById(eavID_2).isPresent()
                                            && patientEavs.get(k).getField().getField_id() == field_id_1){

                                        //calculate the expression value
                                        Float expressionValue = eavRepository.findById(eavID_1).get().getVal() / eavRepository.findById(eavID_2).get().getVal() * multiplier;
                                        //add it to the list
                                        EavID createdID = new EavID(patient_id,patientEavs.get(k).getId().getExam_date(), allFields.get(i).getField_id());
                                        Eav createdEntry = new Eav(createdID,expressionValue,patientRepository.findById(patient_id).get(),allFields.get(i));
                                        newEntries.add(createdEntry);
                                    }
                                }
                            }
                            else if(allFields.get(i).getFrequency().equals("monthly"))
                            {
                                System.out.println("Monthly Percentage");
                                Float counter_1 = (float) 0;
                                Float counter_2 = (float) 0;
                                for(int k = 0; k < patientEavs.size(); k++){
                                    EavID eavID_1 = new EavID(patient_id,patientEavs.get(k).getId().getExam_date(), field_id_1);
                                    EavID eavID_2 = new EavID(patient_id,patientEavs.get(k).getId().getExam_date(), field_id_2);

                                    // if they both exist then we can calculate the monthly percentage
                                    if(eavRepository.findById(eavID_1).isPresent()
                                            && eavRepository.findById(eavID_2).isPresent()
                                            && patientEavs.get(k).getField().getField_id() == field_id_1){
                                        // add their values
                                        counter_1 += eavRepository.findById(eavID_1).get().getVal();
                                        counter_2 += eavRepository.findById(eavID_2).get().getVal();
                                    }
                                }
                                //calculate the expression value
                                float expressionValue = counter_1 / counter_2 * multiplier;
                                //add it to the list
                                EavID createdID = new EavID(patient_id, endDate, allFields.get(i).getField_id());
                                Eav createdEntry = new Eav(createdID, Float.parseFloat(String.valueOf(Math.round(expressionValue))), patientRepository.findById(patient_id).get(), allFields.get(i));
                                newEntries.add(createdEntry);
                            }
                        }
                        //Count checker - 2nd pattern
                        else if(matcher.groupCount() == 2)
                        {
                            System.out.println("Count check");
                        }
                    }
                }
            }
        }


        // because we added entries in the end of patienEavs
        // sort the patientEavs by date
        patientEavs.addAll(newEntries);
        patientEavs.sort((eav1, eav2) -> eav1.getId().getExam_date().compareTo(eav2.getId().getExam_date()));
        for(int i = 0 ; i < patientEavs.size(); i++){
            System.out.println(patientEavs.get(i).getVal()+ " " +patientEavs.get(i).getId().getExam_date());
        }
        return patientEavs;
    }
}
