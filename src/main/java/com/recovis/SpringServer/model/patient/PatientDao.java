package com.recovis.SpringServer.model.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientDao {

    @Autowired
    private PatientRepository repository;

    public Patient save(Patient patient){
        return repository.save(patient);
    }

    public void delete(Patient patient){
        repository.delete(patient);
    }

    public List<Patient> getAllPatients(){
        List<Patient> patients = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(patients::add);
        return patients;
    }

    public Optional<Patient> searchPatient(String username, String password){
        List<Patient> patients;
        patients = getAllPatients();
        for(int i = 0 ; i < patients.size(); i++) {
            if(patients.get(i).getUsername().equals(username) && patients.get(i).getUserpassword().equals(password))
            {
                return Optional.of((patients.get(i)));
            }
        }
        return Optional.empty();
    }
}
