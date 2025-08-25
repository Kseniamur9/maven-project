package org.example.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.OptionalDouble;
import java.util.Set;

public class Statistics {
    private StudyProfile studyProfile;
    private OptionalDouble avgExamScore;
    private int studentCount;
    private int universityCount;
    private Set<String> universityNames;

    public Statistics(StudyProfile studyProfile, OptionalDouble avgExamScore, int studentCount, int universityCount, Set<String> universityNames) {
        this.studyProfile = studyProfile;
        this.avgExamScore = avgExamScore;
        this.studentCount = studentCount;
        this.universityCount = universityCount;
        this.universityNames = universityNames;
    }

    public StudyProfile getStudyProfile() {
        return studyProfile;
    }

    public OptionalDouble getAvgExamScore() {
        return avgExamScore;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public int getUniversityCount() {
        return universityCount;
    }

    public Set<String> getUniversityNames() {
        return universityNames;
    }

    public String getAvgExamScoreAsString() {
        return avgExamScore.isPresent()
                ? new BigDecimal(avgExamScore.getAsDouble())
                .setScale(2, RoundingMode.HALF_UP)
                .toString()
                : "N/A";
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "studyProfile=" + studyProfile.getProfileName() +
                ", avgExamScore=" + getAvgExamScoreAsString() +
                ", studentCount=" + studentCount +
                ", universityCount=" + universityCount +
                ", universityNames=" + universityNames +
                '}';
    }
}