package codesgesture.app.clinax.Models;

import java.io.Serializable;

public class UserModel implements Serializable {
    private String id;
    private String auto_id;
    private String patient_id;
    private String patient_prefix = null;
    private String patient_name;
    private String patient_gender = null;
    private String patient_age = null;
    private String patient_father = null;
    private String patient_mother = null;
    private String patient_husband = null;
    private String patient_mobile_no_1;
    private String patient_mobile_no_2 = null;
    private String patient_email = null;
    private String patient_password;
    private String patient_address = null;
    private String patient_photo = null;
    private String patient_state_id = null;
    private String patient_state_name = null;
    private String patient_city_id = null;
    private String patient_city_name = null;
    private String patient_create_date;
    private String patient_create_time;
    private String patient_modify_date = null;
    private String otp_verify = null;
    private String patient_status;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getAuto_id() {
        return auto_id;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public String getPatient_prefix() {
        return patient_prefix;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public String getPatient_gender() {
        return patient_gender;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public String getPatient_father() {
        return patient_father;
    }

    public String getPatient_mother() {
        return patient_mother;
    }

    public String getPatient_husband() {
        return patient_husband;
    }

    public String getPatient_mobile_no_1() {
        return patient_mobile_no_1;
    }

    public String getPatient_mobile_no_2() {
        return patient_mobile_no_2;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public String getPatient_password() {
        return patient_password;
    }

    public String getPatient_address() {
        return patient_address;
    }

    public String getPatient_photo() {
        return patient_photo;
    }

    public String getPatient_state_id() {
        return patient_state_id;
    }

    public String getPatient_state_name() {
        return patient_state_name;
    }

    public String getPatient_city_id() {
        return patient_city_id;
    }

    public String getPatient_city_name() {
        return patient_city_name;
    }

    public String getPatient_create_date() {
        return patient_create_date;
    }

    public String getPatient_create_time() {
        return patient_create_time;
    }

    public String getPatient_modify_date() {
        return patient_modify_date;
    }

    public String getOtp_verify() {
        return otp_verify;
    }

    public String getPatient_status() {
        return patient_status;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setAuto_id(String auto_id) {
        this.auto_id = auto_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public void setPatient_prefix(String patient_prefix) {
        this.patient_prefix = patient_prefix;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public void setPatient_gender(String patient_gender) {
        this.patient_gender = patient_gender;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public void setPatient_father(String patient_father) {
        this.patient_father = patient_father;
    }

    public void setPatient_mother(String patient_mother) {
        this.patient_mother = patient_mother;
    }

    public void setPatient_husband(String patient_husband) {
        this.patient_husband = patient_husband;
    }

    public void setPatient_mobile_no_1(String patient_mobile_no_1) {
        this.patient_mobile_no_1 = patient_mobile_no_1;
    }

    public void setPatient_mobile_no_2(String patient_mobile_no_2) {
        this.patient_mobile_no_2 = patient_mobile_no_2;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public void setPatient_password(String patient_password) {
        this.patient_password = patient_password;
    }

    public void setPatient_address(String patient_address) {
        this.patient_address = patient_address;
    }

    public void setPatient_photo(String patient_photo) {
        this.patient_photo = patient_photo;
    }

    public void setPatient_state_id(String patient_state_id) {
        this.patient_state_id = patient_state_id;
    }

    public void setPatient_state_name(String patient_state_name) {
        this.patient_state_name = patient_state_name;
    }

    public void setPatient_city_id(String patient_city_id) {
        this.patient_city_id = patient_city_id;
    }

    public void setPatient_city_name(String patient_city_name) {
        this.patient_city_name = patient_city_name;
    }

    public void setPatient_create_date(String patient_create_date) {
        this.patient_create_date = patient_create_date;
    }

    public void setPatient_create_time(String patient_create_time) {
        this.patient_create_time = patient_create_time;
    }

    public void setPatient_modify_date(String patient_modify_date) {
        this.patient_modify_date = patient_modify_date;
    }

    public void setOtp_verify(String otp_verify) {
        this.otp_verify = otp_verify;
    }

    public void setPatient_status(String patient_status) {
        this.patient_status = patient_status;
    }
}
