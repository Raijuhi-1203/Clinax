package codesgesture.app.clinax.Models;

import java.io.Serializable;

public class BookingModel implements Serializable {
    private String id;
    private String auto_id;
    private String booking_id;
    private String token_no;
    private String doctor_id;
    private String doctor_name;
    private String patient_id;
    private String patient_name;
    private String booking_date;
    private String booking_time;
    private String schedule_date;
    private String schedule_time;
    private String generated_date = null;
    private String generated_time = null;
    private String admin_charge = null;
    private String registration_amount;
    private String total_booking_amount;
    private String booking_status;
    private String payment_status;
    private String booking_type;
    private String access_token_no = null;
    private String doctors_clinic_name;
    private String doctors_address;
    private String doctors_state_name;
    private String doctors_city_name;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getAuto_id() {
        return auto_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public String getToken_no() {
        return token_no;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public String getSchedule_date() {
        return schedule_date;
    }

    public String getSchedule_time() {
        return schedule_time;
    }

    public String getGenerated_date() {
        return generated_date;
    }

    public String getGenerated_time() {
        return generated_time;
    }

    public String getAdmin_charge() {
        return admin_charge;
    }

    public String getRegistration_amount() {
        return registration_amount;
    }

    public String getTotal_booking_amount() {
        return total_booking_amount;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public String getBooking_type() {
        return booking_type;
    }

    public String getAccess_token_no() {
        return access_token_no;
    }

    public String getDoctors_clinic_name() {
        return doctors_clinic_name;
    }

    public String getDoctors_address() {
        return doctors_address;
    }

    public String getDoctors_state_name() {
        return doctors_state_name;
    }

    public String getDoctors_city_name() {
        return doctors_city_name;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setAuto_id(String auto_id) {
        this.auto_id = auto_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public void setToken_no(String token_no) {
        this.token_no = token_no;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public void setSchedule_date(String schedule_date) {
        this.schedule_date = schedule_date;
    }

    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }

    public void setGenerated_date(String generated_date) {
        this.generated_date = generated_date;
    }

    public void setGenerated_time(String generated_time) {
        this.generated_time = generated_time;
    }

    public void setAdmin_charge(String admin_charge) {
        this.admin_charge = admin_charge;
    }

    public void setRegistration_amount(String registration_amount) {
        this.registration_amount = registration_amount;
    }

    public void setTotal_booking_amount(String total_booking_amount) {
        this.total_booking_amount = total_booking_amount;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public void setBooking_type(String booking_type) {
        this.booking_type = booking_type;
    }

    public void setAccess_token_no(String access_token_no) {
        this.access_token_no = access_token_no;
    }

    public void setDoctors_clinic_name(String doctors_clinic_name) {
        this.doctors_clinic_name = doctors_clinic_name;
    }

    public void setDoctors_address(String doctors_address) {
        this.doctors_address = doctors_address;
    }

    public void setDoctors_state_name(String doctors_state_name) {
        this.doctors_state_name = doctors_state_name;
    }

    public void setDoctors_city_name(String doctors_city_name) {
        this.doctors_city_name = doctors_city_name;
    }
}
