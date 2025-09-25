package org.example.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class StatisticsEntry {
    @XmlElement(name = "universityProfile")
    private String universityProfile;

    @XmlElement(name = "avgScore")
    private float avgScore;

    public StatisticsEntry() {}

    public StatisticsEntry(String universityProfile, float avgScore) {
        this.universityProfile = universityProfile;
        this.avgScore = avgScore;
    }

    // Геттеры и сеттеры
    public String getUniversityProfile() { return universityProfile; }
    public void setUniversityProfile(String universityProfile) { this.universityProfile = universityProfile; }
    public float getAvgScore() { return avgScore; }
    public void setAvgScore(float avgScore) { this.avgScore = avgScore; }
}
