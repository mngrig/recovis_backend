package com.recovis.SpringServer.model.patient_profile;

import com.recovis.SpringServer.model.all_fields.AllFields;
import com.recovis.SpringServer.model.patient.Patient;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PatientProfileID implements Serializable {

    @ManyToOne
    @JoinColumn(name="patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name="field_id")
    private AllFields field;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public AllFields getField() {
        return field;
    }

    public void setField(AllFields field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "PatientProfileID{" +
                "patient=" + patient +
                ", field=" + field +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientProfileID that = (PatientProfileID) o;
        return Objects.equals(patient, that.patient) && Objects.equals(field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patient, field);
    }
}
