package org.example;

import org.example.model.Student;
import org.example.model.StudyProfile;
import org.example.model.University;

public class Main {
    public static void main(String[] args) {

        University university = new University()
                .setId("UNIV001")
                .setFullName("Московский государственный университет")
                .setShortName("МГУ")
                .setYearOfFoundation(1755)
                .setMainProfile(StudyProfile.COMPUTER_SCIENCE);


        Student student1 = new Student()
                .setFullName("Иван Петров")
                .setUniversityId("UNIV001")
                .setCurrentCourseNumber(2)
                .setAvgExamScore(4.8f);

        Student student2 = new Student()
                .setFullName("Мария Сидорова")
                .setUniversityId("UNIV001")
                .setCurrentCourseNumber(3)
                .setAvgExamScore(4.5f);


        System.out.println(university);
        System.out.println(student1);
        System.out.println(student2);
    }
}