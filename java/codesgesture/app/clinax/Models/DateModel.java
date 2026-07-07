package codesgesture.app.clinax.Models;

import java.io.Serializable;

public class DateModel implements Serializable {
    private String schedule_date;


    // Getter Methods

    public String getSchedule_date() {
        return schedule_date;
    }

    // Setter Methods

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }
}
