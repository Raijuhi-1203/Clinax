package codesgesture.app.clinax.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CityModel implements Serializable {
    private String id;
    private String state_id;
    private String district_id;
    private String district_name;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getState_id() {
        return state_id;
    }

    public String getDistrict_id() {
        return district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public void setDistrict_id(String district_id) {
        this.district_id = district_id;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    @NonNull
    @Override
    public String toString() {
        return district_name;
    }
}
