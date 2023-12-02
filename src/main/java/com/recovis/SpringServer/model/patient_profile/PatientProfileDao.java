package com.recovis.SpringServer.model.patient_profile;

import com.recovis.SpringServer.model.all_fields.AllFields;
import com.recovis.SpringServer.model.patient.Patient;
import com.recovis.SpringServer.model.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientProfileDao {


    @Autowired
    private PatientProfileRepository repository;

    private PatientRepository rep;

    public PatientProfile save(PatientProfile patientProfile){
        return repository.save(patientProfile);
    }

    public void delete(PatientProfile patientProfile){
        repository.delete(patientProfile);
    }

    public List<PatientProfile> getAllPatientProfiles(){
        List<PatientProfile> patients = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(patients::add);
        return patients;
    }

    public List<PatientProfile> getPatientProfile(String patient_id){

        List<PatientProfile> patientProfile = new ArrayList<>();


        return patientProfile;
    }





}
