package com.recovis.SpringServer.model.patient_profile;

import com.recovis.SpringServer.model.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientProfileDao {


    @Autowired
    private PatientProfileRepository repository;

    public PatientProfile save(PatientProfile patientProfile){
        return repository.save(patientProfile);
    }

    public void delete(PatientProfile patientProfile){
        repository.delete(patientProfile);
    }

}
