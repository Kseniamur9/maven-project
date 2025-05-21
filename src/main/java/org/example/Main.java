package org.example;

import org.example.comparator.StudentComparator;
import org.example.comparator.StudentComparatorType;
import org.example.comparator.UniversityComparator;
import org.example.comparator.UniversityComparatorType;
import org.example.model.Student;
import org.example.model.StudyProfile;
import org.example.model.University;
import org.example.util.ComparatorUtil;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        University university1 = new University.Builder()
                .setId("UNIV001")
                .setFullName("Московский государственный университет")
                .setShortName("МГУ")
                .setYearOfFoundation(1755)
                .setMainProfile(StudyProfile.COMPUTER_SCIENCE)
                .build();

        University university2 = new University.Builder()
                .setId("UNIV002")
                .setFullName("Санкт-Петербургский государственный университет")
                .setShortName("СПбГУ")
                .setYearOfFoundation(1724)
                .setMainProfile(StudyProfile.MEDICINE)
                .build();

        Student student1 = new Student.Builder()
                .setFullName("Иван Иванов")
                .setUniversityId("UNIV001")
                .setCurrentCourseNumber(2)
                .setAvgExamScore(4.8f)
                .build();

        Student student2 = new Student.Builder()
                .setFullName("Анна Петрова")
                .setUniversityId("UNIV001")
                .setCurrentCourseNumber(1)
                .setAvgExamScore(4.9f)
                .build();

        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.FULL_NAME);
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);

        List<University> universities = Arrays.asList(university1, university2);
        List<Student> students = Arrays.asList(student1, student2);

        System.out.println("Universities sorted by full name:");
        universities.stream()
                .sorted(universityComparator)
                .forEach(System.out::println);

        System.out.println("\nStudents sorted by average exam score (descending):");
        students.stream()
                .sorted(studentComparator)
                .forEach(System.out::println);
    }
}