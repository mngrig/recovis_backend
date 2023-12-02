package com.recovis.SpringServer.controller;

import com.recovis.SpringServer.model.patient.Patient;
import com.recovis.SpringServer.model.patient.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PatientController {

    @Autowired
    private PatientDao patientDao;

    @GetMapping("/patient/get-all")
    public List<Patient> getAllPatients(){
        return patientDao.getAllPatients();
    }

    @PostMapping("/patient/save-patient")
    public Patient save(@RequestBody Patient patient){
        return patientDao.save(patient);
    }

    @GetMapping("/patient/get-patient")
    public Optional<Patient> searchPatient(@RequestParam String username, @RequestParam String password)
    {
        return patientDao.searchPatient(username,password);
    }
}

