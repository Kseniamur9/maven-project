package org.example;

import org.example.comparator.StudentComparator;
import org.example.comparator.StudentComparatorType;
import org.example.comparator.UniversityComparator;
import org.example.comparator.UniversityComparatorType;
import org.example.model.Student;
import org.example.model.StudyProfile;
import org.example.model.University;
import org.example.model.Statistics;
import org.example.util.ComparatorUtil;
import org.example.util.JsonUtil;
import org.example.util.StatisticsUtil;
import org.example.util.XlsWriter;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание тестовых данных
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

        University university3 = new University.Builder()
                .setId("UNIV003")
                .setFullName("Новосибирский государственный университет")
                .setShortName("НГУ")
                .setYearOfFoundation(1959)
                .setMainProfile(StudyProfile.ENGINEERING)
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

        List<University> universities = Arrays.asList(university1, university2, university3);
        List<Student> students = Arrays.asList(student1, student2);

        // Сериализация коллекций
        System.out.println("=== Сериализация коллекций ===");
        String universitiesJson = JsonUtil.serializeUniversities(universities);
        System.out.println("Universities JSON:\n" + universitiesJson);
        String studentsJson = JsonUtil.serializeStudents(students);
        System.out.println("Students JSON:\n" + studentsJson);

        // Десериализация коллекций
        System.out.println("\n=== Десериализация коллекций ===");
        List<University> deserializedUniversities = JsonUtil.deserializeUniversities(universitiesJson);
        List<Student> deserializedStudents = JsonUtil.deserializeStudents(studentsJson);

        // Проверка количества элементов
        System.out.println("Исходное количество университетов: " + universities.size());
        System.out.println("Десериализованное количество университетов: " + deserializedUniversities.size());
        System.out.println("Исходное количество студентов: " + students.size());
        System.out.println("Десериализованное количество студентов: " + deserializedStudents.size());

        // Сериализация и десериализация через Stream API
        System.out.println("\n=== Stream API: Сериализация и десериализация отдельных объектов ===");
        System.out.println("Университеты:");
        universities.stream()
                .map(university -> {
                    String json = JsonUtil.serializeUniversity(university);
                    System.out.println("Serialized University JSON:\n" + json);
                    University deserialized = JsonUtil.deserializeUniversity(json);
                    System.out.println("Deserialized University:\n" + deserialized);
                    return deserialized;
                })
                .forEach(university -> {});

        System.out.println("\nСтуденты:");
        students.stream()
                .map(student -> {
                    String json = JsonUtil.serializeStudent(student);
                    System.out.println("Serialized Student JSON:\n" + json);
                    Student deserialized = JsonUtil.deserializeStudent(json);
                    System.out.println("Deserialized Student:\n" + deserialized);
                    return deserialized;
                })
                .forEach(student -> {});

        // Сортировка
        UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.FULL_NAME);
        StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);

        System.out.println("\n=== Сортировка ===");
        System.out.println("Universities sorted by full name:");
        universities.stream()
                .sorted(universityComparator)
                .forEach(System.out::println);

        System.out.println("\nStudents sorted by average exam score (descending):");
        students.stream()
                .sorted(studentComparator)
                .forEach(System.out::println);

        // Сбор и вывод статистики
        System.out.println("\n=== Статистика ===");
        List<Statistics> statistics = StatisticsUtil.calculateStatistics(students, universities);
        statistics.forEach(System.out::println);

        // Запись статистики в Excel
        System.out.println("\n=== Запись статистики в Excel ===");
        XlsWriter.writeStatisticsToExcel(statistics, "statistics.xlsx");
        System.out.println("Excel file generated: statistics.xlsx");
    }
}