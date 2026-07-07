package codesgesture.app.clinax.Models;

import java.io.Serializable;

public class FeedbackModel implements Serializable {
    private String id;
    private String doctor_id;
    private String reviewer_id;
    private String reviewer_name;
    private String reviewer_rating;
    private String reviewer_comments;
    private String reviewer_date;
    private String reviewer_status;


    // Getter Methods

    public String getId() {
        return id;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public String getReviewer_id() {
        return reviewer_id;
    }

    public String getReviewer_name() {
        return reviewer_name;
    }

    public String getReviewer_rating() {
        return reviewer_rating;
    }

    public String getReviewer_comments() {
        return reviewer_comments;
    }

    public String getReviewer_date() {
        return reviewer_date;
    }

    public String getReviewer_status() {
        return reviewer_status;
    }

    // Setter Methods

    public void setId(String id) {
        this.id = id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public void setReviewer_id(String reviewer_id) {
        this.reviewer_id = reviewer_id;
    }

    public void setReviewer_name(String reviewer_name) {
        this.reviewer_name = reviewer_name;
    }

    public void setReviewer_rating(String reviewer_rating) {
        this.reviewer_rating = reviewer_rating;
    }

    public void setReviewer_comments(String reviewer_comments) {
        this.reviewer_comments = reviewer_comments;
    }

    public void setReviewer_date(String reviewer_date) {
        this.reviewer_date = reviewer_date;
    }

    public void setReviewer_status(String reviewer_status) {
        this.reviewer_status = reviewer_status;
    }
}
