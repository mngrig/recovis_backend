package com.recovis.SpringServer.controller;

import com.recovis.SpringServer.model.patient.Patient;
import com.recovis.SpringServer.model.patient.PatientDao;
import com.recovis.SpringServer.model.patient_profile.PatientProfile;
import com.recovis.SpringServer.model.patient_profile.PatientProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientProfileController {

    @Autowired
    private PatientProfileDao patientProfileDao;

    @GetMapping("/patient_profile/get-all")
    public List<PatientProfile> getAllPatients(){
        return patientProfileDao.getAllPatientProfiles();
    }

    @PostMapping("/patient_profile/save-patient")
    public PatientProfile save(@RequestBody PatientProfile patientProfile){
        return patientProfileDao.save(patientProfile);
    }

    @GetMapping("/patient_profile/get-patient-profile")
    public List<PatientProfile> searchPatient(@RequestParam String patient_id)
    {
        return patientProfileDao.getPatientProfile(patient_id);
    }

}
