package com.recovis.SpringServer;

import com.recovis.SpringServer.model.all_fields.AllFields;
import com.recovis.SpringServer.model.all_fields.AllFieldsDao;
import com.recovis.SpringServer.model.patient.Patient;
import com.recovis.SpringServer.model.patient.PatientDao;
import com.recovis.SpringServer.model.patient_profile.PatientProfile;
import com.recovis.SpringServer.model.patient_profile.PatientProfileDao;
import com.recovis.SpringServer.model.patient_profile.PatientProfileID;
import org.hibernate.type.descriptor.jdbc.IntegerJdbcType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringServerApplicationTests {

	@Autowired
	private PatientDao patientdao;
	@Autowired
	private AllFieldsDao allFieldsDao;
	@Autowired
	private PatientProfileDao patientProfileDao;

	//@Test
	void addPatientTest() {
		addPatient("p02", "Nikos", "Nikoglou", null, null, null, null,
				null, null, "niknik", "pwd1");
	}
	private void addPatient(String patient_id, String first_name, String second_name, String tel, String email,
							Date last_transplant_date, String transplant_type, Integer transplants_num, String kidney_failure_cause,
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

	@Test
	void addPatientProfile() {
		Optional<Patient> p = patientdao.searchPatient("manosgrig","123");
		Optional<AllFields> f = allFieldsDao.searchField("Πίεση Συστολική");
		if(p.isPresent() && f.isPresent()){
			addPatientProfile(p.get(),f.get(),1,null);
		}
	}
	private void addPatientProfile(Patient patient, AllFields field, int required, String guideline)
	{
		PatientProfile patientProfile = new PatientProfile();
		PatientProfileID patientProfileID = new PatientProfileID();
		patientProfileID.setPatient(patient);
		patientProfileID.setField(field);
		patientProfile.setId(patientProfileID);
		patientProfile.setRequired(required);
		patientProfile.setGuideline(guideline);

		patientProfileDao.save(patientProfile);

	}



}
