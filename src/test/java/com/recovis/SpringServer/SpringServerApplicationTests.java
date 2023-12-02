package com.recovis.SpringServer;

import com.recovis.SpringServer.model.patient.Patient;
import com.recovis.SpringServer.model.patient.PatientDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

@SpringBootTest
class SpringServerApplicationTests {

	@Autowired
	private PatientDao patientdao;

	//@Test
	void addPatientTest() {
		addPatient("p02","Kostas","Kostoglou",null,null,null,null,
		0,null,"koskos","pwd");
	}

	private void addPatient(String patient_id, String first_name, String second_name, String tel, String email,
							Date last_transplant_date, String transplant_type, int transplants_num,String kidney_failure_cause,
							String username, String userpassword)
	{
		Patient patient = new Patient();
		patient.setPatient_id(patient_id);
		patient.setFirst_name(first_name);
		patient.setSecond_name(second_name);
		patient.setUsername(username);
		patient.setUserpassword(userpassword);
		patientdao.save(patient);
	}



}
