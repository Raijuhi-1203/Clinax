package codesgesture.app.clinax.Models;

import java.io.Serializable;

public class SpecialityModel implements Serializable {
    private String id;
    private String speciality_name;
    private String speciality_status;
    private String speciality_icon;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getSpeciality_name() {
        return speciality_name;
    }

    public String getSpeciality_status() {
        return speciality_status;
    }

    public String getSpeciality_icon() {
        return speciality_icon;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setSpeciality_name(String speciality_name) {
        this.speciality_name = speciality_name;
    }

    public void setSpeciality_status(String speciality_status) {
        this.speciality_status = speciality_status;
    }

    public void setSpeciality_icon(String speciality_icon) {
        this.speciality_icon = speciality_icon;
    }
}