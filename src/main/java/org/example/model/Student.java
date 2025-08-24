package org.example.model;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Student {
    @SerializedName("student_name")
    private String fullName;
    @SerializedName("university_id")
    private String universityId;
    @SerializedName("course")
    private int currentCourseNumber;
    @SerializedName("average_score")
    private float avgExamScore;

    public Student() {
    }

    private Student(Builder builder) {
        this.fullName = builder.fullName;
        this.universityId = builder.universityId;
        this.currentCourseNumber = builder.currentCourseNumber;
        this.avgExamScore = builder.avgExamScore;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUniversityId() {
        return universityId;
    }

    public int getCurrentCourseNumber() {
        return currentCourseNumber;
    }

    public float getAvgExamScore() {
        return avgExamScore;
    }

    public static class Builder {
        private String fullName;
        private String universityId;
        private int currentCourseNumber;
        private float avgExamScore;

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setUniversityId(String universityId) {
            this.universityId = universityId;
            return this;
        }

        public Builder setCurrentCourseNumber(int currentCourseNumber) {
            this.currentCourseNumber = currentCourseNumber;
            return this;
        }

        public Builder setAvgExamScore(float avgExamScore) {
            this.avgExamScore = avgExamScore;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fullName", fullName)
                .append("universityId", universityId)
                .append("currentCourseNumber", currentCourseNumber)
                .append("avgExamScore", avgExamScore)
                .toString();
    }
}