package org.example.model;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class University {
    @SerializedName("university_id")
    private String id;
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("short_name")
    private String shortName;
    @SerializedName("year_founded")
    private int yearOfFoundation;
    @SerializedName("main_profile")
    private StudyProfile mainProfile;

    public University() {
    }

    private University(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.shortName = builder.shortName;
        this.yearOfFoundation = builder.yearOfFoundation;
        this.mainProfile = builder.mainProfile;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public int getYearOfFoundation() {
        return yearOfFoundation;
    }

    public StudyProfile getMainProfile() {
        return mainProfile;
    }

    public static class Builder {
        private String id;
        private String fullName;
        private String shortName;
        private int yearOfFoundation;
        private StudyProfile mainProfile;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setShortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public Builder setYearOfFoundation(int yearOfFoundation) {
            this.yearOfFoundation = yearOfFoundation;
            return this;
        }

        public Builder setMainProfile(StudyProfile mainProfile) {
            this.mainProfile = mainProfile;
            return this;
        }

        public University build() {
            return new University(this);
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", id)
                .append("fullName", fullName)
                .append("shortName", shortName)
                .append("yearOfFoundation", yearOfFoundation)
                .append("mainProfile", mainProfile != null ? mainProfile.getProfileName() : null)
                .toString();
    }
}