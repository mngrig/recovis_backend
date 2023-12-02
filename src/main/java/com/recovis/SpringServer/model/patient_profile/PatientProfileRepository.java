package com.recovis.SpringServer.model.patient_profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientProfileRepository extends CrudRepository<PatientProfile, PatientProfileID> {



}
