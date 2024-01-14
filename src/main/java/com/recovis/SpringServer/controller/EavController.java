package com.recovis.SpringServer.controller;

import com.recovis.SpringServer.model.all_fields.AllFields;
import com.recovis.SpringServer.model.all_fields.AllFieldsDao;
import com.recovis.SpringServer.model.eav.Eav;
import com.recovis.SpringServer.model.eav.EavDao;
import com.recovis.SpringServer.model.eav.EavID;
import com.recovis.SpringServer.model.patient.Patient;
import com.recovis.SpringServer.model.patient.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class EavController {

    @Autowired
    private EavDao eavDao;

    @Autowired
    private AllFieldsDao allFieldsDao;

    @Autowired
    private PatientDao patientDao;

    @PostMapping("/eav/save-eav")
    public void save(@RequestBody ArrayList<Eav> eav){
        for(int i = 0 ; i < eav.size(); i++)
        {
            String patient_id = eav.get(i).getId().getPatient_id();
            Integer field_id = eav.get(i).getId().getField_id();
            eav.get(i).setPatient(patientDao.getPatient(patient_id).get());
            eav.get(i).setField(allFieldsDao.getField(field_id).get());
        }
        eavDao.saveAll(eav);
    }

    @GetMapping("/eav/get-all-exams")
    public List<Eav> getExams(@RequestParam String patient_id, @RequestParam String start_date, @RequestParam String end_date) throws ParseException {
        return eavDao.getAllExams(patient_id,start_date,end_date);
    }

    @GetMapping("/eav/get-specific-exams")
    public List<Eav> getExams(@RequestParam String patient_id, @RequestParam List<Integer> field_ids, @RequestParam String start_date, @RequestParam String end_date)
    {
        List<Eav> allEavs = eavDao.getAllEAVs();
        List<Eav> examsValues = new ArrayList<>();
        Date startDate = Date.valueOf(start_date);
        Date endDate = Date.valueOf(end_date);

        for(int i = 0 ; i < allEavs.size(); i++){
            Eav entry = allEavs.get(i);
            Date entryDate = entry.getId().getExam_date();
            for(int j = 0; j < field_ids.size(); j++){
                if(patient_id.equals(entry.getId().getPatient_id()) && field_ids.get(j).equals(entry.getId().getField_id())
                        && entryDate.before(endDate) && entryDate.after(startDate)){
                    examsValues.add(entry);
                }
            }
        }
        return examsValues;
    }
}
