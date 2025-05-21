package org.example.model;

public enum StudyProfile {
    MEDICINE("Медицина"),
    COMPUTER_SCIENCE("Информатика"),
    ENGINEERING("Инженерия"),
    ECONOMICS("Экономика"),
    LAW("Юриспруденция");

    private final String profileName;

    StudyProfile(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return profileName;
    }
}
