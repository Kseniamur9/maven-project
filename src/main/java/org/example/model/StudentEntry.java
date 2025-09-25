package org.example.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class StudentEntry {
    @XmlElement(name = "studentName")
    private String studentName;

    @XmlElement(name = "universityId")
    private String universityId;

    @XmlElement(name = "avgScore")
    private float avgScore;

    public StudentEntry() {}

    public StudentEntry(String studentName, String universityId, float avgScore) {
        this.studentName = studentName;
        this.universityId = universityId;
        this.avgScore = avgScore;
    }

    // Геттеры и сеттеры
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getUniversityId() { return universityId; }
    public void setUniversityId(String universityId) { this.universityId = universityId; }
    public float getAvgScore() { return avgScore; }
    public void setAvgScore(float avgScore) { this.avgScore = avgScore; }
}