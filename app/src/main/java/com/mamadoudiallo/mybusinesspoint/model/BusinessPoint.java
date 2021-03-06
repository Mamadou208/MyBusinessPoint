package com.mamadoudiallo.mybusinesspoint.model;

/**
 * Created by Home on 16/01/2016.
 */
public class BusinessPoint {
    private int id;
    private String subject, detail, teacher, grade, status;

    public BusinessPoint() {
    }

    public BusinessPoint(int id) {
        this.id = id;
    }

    public BusinessPoint(int id, String subject, String detail, String teacher, String grade,  String status) {
        this.id = id;
        this.subject = subject;
        this.detail = detail;
        this.teacher = teacher;
        this.grade = grade;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "BusinessPoint{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", detail='" + detail + '\'' +
                ", teacher='" + teacher + '\'' +
                ", grade='" + grade + '\'' +
                ", status=" + status +
                '}';
    }
}
