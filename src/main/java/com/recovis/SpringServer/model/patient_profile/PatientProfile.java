package com.recovis.SpringServer.model.patient_profile;

import com.recovis.SpringServer.model.all_fields.AllFields;
import com.recovis.SpringServer.model.patient.Patient;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Entity
public class PatientProfile {

    @EmbeddedId
    private PatientProfileID id;

    @NonNull
    private int required;
    @Nullable
    private String guideline;

    public PatientProfileID getId() {
        return id;
    }

    public void setId(PatientProfileID id) {
        this.id = id;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }

    @Nullable
    public String getGuideline() {
        return guideline;
    }

    public void setGuideline(@Nullable String guideline) {
        this.guideline = guideline;
    }

    @Override
    public String toString() {
        return "PatientProfile{" +
                "id=" + id +
                "patient=" + id.getPatient().toString() +
                "fields=" + id.getField().toString() +
                ", required=" + required +
                ", guideline='" + guideline + '\'' +
                '}';
    }
}
