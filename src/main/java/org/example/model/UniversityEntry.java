package org.example.model;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class UniversityEntry {
    @XmlElement(name = "universityId")
    private String universityId;

    @XmlElement(name = "universityName")
    private String universityName;

    @XmlElement(name = "universityProfile")
    private String universityProfile;

    public UniversityEntry() {}

    public UniversityEntry(String universityId, String universityName, String universityProfile) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.universityProfile = universityProfile;
    }

    // Геттеры и сеттеры
    public String getUniversityId() { return universityId; }
    public void setUniversityId(String universityId) { this.universityId = universityId; }
    public String getUniversityName() { return universityName; }
    public void setUniversityName(String universityName) { this.universityName = universityName; }
    public String getUniversityProfile() { return universityProfile; }
    public void setUniversityProfile(String universityProfile) { this.universityProfile = universityProfile; }
}
