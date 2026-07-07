package codesgesture.app.clinax.Models;

import java.io.Serializable;

public class SlotModel implements Serializable {
    private String id;
    private String doctor_id;
    private String data_create_date;
    private String schedule_date;
    private String schedule_time;
    private String schedule_24_time;
    private String schedule_no_of_slot;
    private String schedule_day;
    private String weekly_off;
    private String schedule_status;
    private String schedule_type;
    private String access_token_no = null;
    private String status;
    private String bookingstatus;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getData_create_date() {
        return data_create_date;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public String getSchedule_time() {
        return schedule_time;
    }

    public String getSchedule_24_time() {
        return schedule_24_time;
    }

    public String getSchedule_no_of_slot() {
        return schedule_no_of_slot;
    }

    public String getSchedule_day() {
        return schedule_day;
    }

    public String getWeekly_off() {
        return weekly_off;
    }

    public String getSchedule_status() {
        return schedule_status;
    }

    public String getSchedule_type() {
        return schedule_type;
    }

    public String getAccess_token_no() {
        return access_token_no;
    }

    public String getStatus() {
        return status;
    }

    public String getbookingstatus() {
        return bookingstatus;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setData_create_date(String data_create_date) {
        this.data_create_date = data_create_date;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }

    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }

    public void setSchedule_24_time(String schedule_24_time) {
        this.schedule_24_time = schedule_24_time;
    }

    public void setSchedule_no_of_slot(String schedule_no_of_slot) {
        this.schedule_no_of_slot = schedule_no_of_slot;
    }

    public void setSchedule_day(String schedule_day) {
        this.schedule_day = schedule_day;
    }

    public void setWeekly_off(String weekly_off) {
        this.weekly_off = weekly_off;
    }

    public void setSchedule_status(String schedule_status) {
        this.schedule_status = schedule_status;
    }

    public void setSchedule_type(String schedule_type) {
        this.schedule_type = schedule_type;
    }

    public void setAccess_token_no(String access_token_no) {
        this.access_token_no = access_token_no;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setbookingstatus(String bookingstatus) {
        this.bookingstatus = bookingstatus;
    }
}