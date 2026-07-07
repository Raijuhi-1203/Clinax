package codesgesture.app.clinax.Models;

import java.io.Serializable;

public class DoctorModel implements Serializable {
    private String id;
    private String auto_id;
    private String doctors_id;
    private String doctors_name;
    private String doctors_reg_no;
    private String doctors_clinic_name;
    private String doctors_mobile_no;
    private String doctor_password = null;
    private String department_id;
    private String department_name;
    private String sub_department_name;
    private String symptoms_check;
    private String doctor_description;
    private String doctors_speciality;
    private String doctors_experience;
    private String doctors_qualification;
    private String other_qualification_certification;
    private String medical_council;
    private String doctors_photo;
    private String doctors_by;
    private String doctors_fee;
    private String doctors_address;
    private String doctors_state_id;
    private String doctors_state_name;
    private String doctors_city_id;
    private String doctors_city_name;
    private String doctors_join_date;
    private String doctors_modify_date;
    private String otp_verify;
    private String doctors_status;
    private String access_token_no = null;
    private String start_time = null;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getAval() {
        return aval;
    }

    public void setAval(String aval) {
        this.aval = aval;
    }

    private String end_time = null;
    private String aval = null;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    private String feedback = null;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getAuto_id() {
        return auto_id;
    }

    public String getDoctors_id() {
        return doctors_id;
    }

    public String getDoctors_name() {
        return doctors_name;
    }

    public String getDoctors_reg_no() {
        return doctors_reg_no;
    }

    public String getDoctors_clinic_name() {
        return doctors_clinic_name;
    }

    public String getDoctors_mobile_no() {
        return doctors_mobile_no;
    }

    public String getDoctor_password() {
        return doctor_password;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public String getSub_department_name() {
        return sub_department_name;
    }

    public String getSymptoms_check() {
        return symptoms_check;
    }

    public String getDoctor_description() {
        return doctor_description;
    }

    public String getDoctors_speciality() {
        return doctors_speciality;
    }

    public String getDoctors_experience() {
        return doctors_experience;
    }

    public String getDoctors_qualification() {
        return doctors_qualification;
    }

    public String getOther_qualification_certification() {
        return other_qualification_certification;
    }

    public String getMedical_council() {
        return medical_council;
    }

    public String getDoctors_photo() {
        return doctors_photo;
    }

    public String getDoctors_by() {
        return doctors_by;
    }

    public String getDoctors_fee() {
        return doctors_fee;
    }

    public String getDoctors_address() {
        return doctors_address;
    }

    public String getDoctors_state_id() {
        return doctors_state_id;
    }

    public String getDoctors_state_name() {
        return doctors_state_name;
    }

    public String getDoctors_city_id() {
        return doctors_city_id;
    }

    public String getDoctors_city_name() {
        return doctors_city_name;
    }

    public String getDoctors_join_date() {
        return doctors_join_date;
    }

    public String getDoctors_modify_date() {
        return doctors_modify_date;
    }

    public String getOtp_verify() {
        return otp_verify;
    }

    public String getDoctors_status() {
        return doctors_status;
    }

    public String getAccess_token_no() {
        return access_token_no;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setAuto_id(String auto_id) {
        this.auto_id = auto_id;
    }

    public void setDoctors_id(String doctors_id) {
        this.doctors_id = doctors_id;
    }

    public void setDoctors_name(String doctors_name) {
        this.doctors_name = doctors_name;
    }

    public void setDoctors_reg_no(String doctors_reg_no) {
        this.doctors_reg_no = doctors_reg_no;
    }

    public void setDoctors_clinic_name(String doctors_clinic_name) {
        this.doctors_clinic_name = doctors_clinic_name;
    }

    public void setDoctors_mobile_no(String doctors_mobile_no) {
        this.doctors_mobile_no = doctors_mobile_no;
    }

    public void setDoctor_password(String doctor_password) {
        this.doctor_password = doctor_password;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public void setSub_department_name(String sub_department_name) {
        this.sub_department_name = sub_department_name;
    }

    public void setSymptoms_check(String symptoms_check) {
        this.symptoms_check = symptoms_check;
    }

    public void setDoctor_description(String doctor_description) {
        this.doctor_description = doctor_description;
    }

    public void setDoctors_speciality(String doctors_speciality) {
        this.doctors_speciality = doctors_speciality;
    }

    public void setDoctors_experience(String doctors_experience) {
        this.doctors_experience = doctors_experience;
    }

    public void setDoctors_qualification(String doctors_qualification) {
        this.doctors_qualification = doctors_qualification;
    }

    public void setOther_qualification_certification(String other_qualification_certification) {
        this.other_qualification_certification = other_qualification_certification;
    }

    public void setMedical_council(String medical_council) {
        this.medical_council = medical_council;
    }

    public void setDoctors_photo(String doctors_photo) {
        this.doctors_photo = doctors_photo;
    }

    public void setDoctors_by(String doctors_by) {
        this.doctors_by = doctors_by;
    }

    public void setDoctors_fee(String doctors_fee) {
        this.doctors_fee = doctors_fee;
    }

    public void setDoctors_address(String doctors_address) {
        this.doctors_address = doctors_address;
    }

    public void setDoctors_state_id(String doctors_state_id) {
        this.doctors_state_id = doctors_state_id;
    }

    public void setDoctors_state_name(String doctors_state_name) {
        this.doctors_state_name = doctors_state_name;
    }

    public void setDoctors_city_id(String doctors_city_id) {
        this.doctors_city_id = doctors_city_id;
    }

    public void setDoctors_city_name(String doctors_city_name) {
        this.doctors_city_name = doctors_city_name;
    }

    public void setDoctors_join_date(String doctors_join_date) {
        this.doctors_join_date = doctors_join_date;
    }

    public void setDoctors_modify_date(String doctors_modify_date) {
        this.doctors_modify_date = doctors_modify_date;
    }

    public void setOtp_verify(String otp_verify) {
        this.otp_verify = otp_verify;
    }

    public void setDoctors_status(String doctors_status) {
        this.doctors_status = doctors_status;
    }

    public void setAccess_token_no(String access_token_no) {
        this.access_token_no = access_token_no;
    }
}