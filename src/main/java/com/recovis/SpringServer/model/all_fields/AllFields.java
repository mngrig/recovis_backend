package com.recovis.SpringServer.model.all_fields;

import com.recovis.SpringServer.model.patient_profile.PatientProfile;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Set;

@Entity
public class AllFields {

    @Id
    private int field_id;
    @NonNull
    private String description_gr;
    @NonNull
    private String description_en;
    @NonNull
    private String frequency;
    @NonNull
    private String measurement_unit;
    @NonNull
    private String acceptable_range;
    @Nullable
    private Integer interdependence;

    public int getField_id() {
        return field_id;
    }

    public void setField_id(int field_id) {
        this.field_id = field_id;
    }

    @NonNull
    public String getDescription_gr() {
        return description_gr;
    }

    public void setDescription_gr(@NonNull String description_gr) {
        this.description_gr = description_gr;
    }

    @NonNull
    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(@NonNull String description_en) {
        this.description_en = description_en;
    }

    @NonNull
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(@NonNull String frequency) {
        this.frequency = frequency;
    }

    @NonNull
    public String getMeasurement_unit() {
        return measurement_unit;
    }

    public void setMeasurement_unit(@NonNull String measurement_unit) {
        this.measurement_unit = measurement_unit;
    }

    @NonNull
    public String getAcceptable_range() {
        return acceptable_range;
    }

    public void setAcceptable_range(@NonNull String acceptable_range) {
        this.acceptable_range = acceptable_range;
    }

    @Nullable
    public Integer getInterdependence() {
        return interdependence;
    }

    public void setInterdependence(@Nullable Integer interdependence) {
        this.interdependence = interdependence;
    }
}
