package codesgesture.app.clinax.Models;

import java.io.Serializable;

public class HProblemModel implements Serializable {
    private String id;
    private String symptoms_name;
    private String symptoms_icon;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getSymptoms_name() {
        return symptoms_name;
    }

    public String getSymptoms_icon() {
        return symptoms_icon;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setSymptoms_name(String symptoms_name) {
        this.symptoms_name = symptoms_name;
    }

    public void setSymptoms_icon(String symptoms_icon) {
        this.symptoms_icon = symptoms_icon;
    }
}