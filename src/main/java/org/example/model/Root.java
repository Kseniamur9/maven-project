package org.example.model;

import javax.xml.bind.annotation.*;
import java.time.ZonedDateTime;
import java.util.List;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {
    @XmlElementWrapper(name = "studentsInfo")
    @XmlElement(name = "studentEntry")
    private List<StudentEntry> studentsInfo;

    @XmlElementWrapper(name = "universitiesInfo")
    @XmlElement(name = "universityEntry")
    private List<UniversityEntry> universitiesInfo;

    @XmlElementWrapper(name = "statisticalInfo")
    @XmlElement(name = "statisticsEntry")
    private List<StatisticsEntry> statisticalInfo;

    @XmlElement(name = "processedAt")
    private ZonedDateTime processedAt;

    public Root() {
        this.processedAt = ZonedDateTime.now();
    }

    public Root(List<StudentEntry> studentsInfo, List<UniversityEntry> universitiesInfo, List<StatisticsEntry> statisticalInfo) {
        this.studentsInfo = studentsInfo;
        this.universitiesInfo = universitiesInfo;
        this.statisticalInfo = statisticalInfo;
        this.processedAt = ZonedDateTime.now();
    }

    // Геттеры и сеттеры
    public List<StudentEntry> getStudentsInfo() { return studentsInfo; }
    public void setStudentsInfo(List<StudentEntry> studentsInfo) { this.studentsInfo = studentsInfo; }
    public List<UniversityEntry> getUniversitiesInfo() { return universitiesInfo; }
    public void setUniversitiesInfo(List<UniversityEntry> universitiesInfo) { this.universitiesInfo = universitiesInfo; }
    public List<StatisticsEntry> getStatisticalInfo() { return statisticalInfo; }
    public void setStatisticalInfo(List<StatisticsEntry> statisticalInfo) { this.statisticalInfo = statisticalInfo; }
    public ZonedDateTime getProcessedAt() { return processedAt; }
    public void setProcessedAt(ZonedDateTime processedAt) { this.processedAt = processedAt; }
}