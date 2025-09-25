package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.comparator.StudentComparator;
import org.example.comparator.StudentComparatorType;
import org.example.comparator.UniversityComparator;
import org.example.comparator.UniversityComparatorType;
import org.example.model.*;
import org.example.util.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            System.setProperty("log4j2.configurationFile", "classpath:log4j2.xml");
            logger.info("Запуск приложения");

            // Тестовые данные
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

            // Сортировка
            logger.info("Сортировка университетов по полному названию");
            UniversityComparator universityComparator = ComparatorUtil.getUniversityComparator(UniversityComparatorType.FULL_NAME);
            universities.sort(universityComparator);

            logger.info("Сортировка студентов по средней оценке за экзамены (по убыванию)");
            StudentComparator studentComparator = ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);
            students.sort(studentComparator);

            // Сбор статистики
            logger.info("Вычисление статистики");
            List<Statistics> statistics = StatisticsUtil.calculateStatistics(students, universities);

            // Запись статистики в Excel
            logger.info("Запись статистики в Excel-файл: statistics.xlsx");
            XlsWriter.writeStatisticsToExcel(statistics, "statistics.xlsx");
            logger.info("Excel-файл успешно создан");

            // Создание объекта Root для XML и JSON
            List<StudentEntry> studentEntries = students.stream()
                    .map(s -> new StudentEntry(s.getFullName(), s.getUniversityId(), s.getAvgExamScore()))
                    .collect(Collectors.toList());

            List<UniversityEntry> universityEntries = universities.stream()
                    .map(u -> new UniversityEntry(u.getId(), u.getFullName(), u.getMainProfile().name()))
                    .collect(Collectors.toList());

            List<StatisticsEntry> statisticsEntries = statistics.stream()
                    .map(s -> new StatisticsEntry(s.getStudyProfile().name(), s.getAvgExamScoreAsString().equals("N/A") ? 0.0f : Float.parseFloat(s.getAvgExamScoreAsString())))
                    .collect(Collectors.toList());

            Root root = new Root(studentEntries, universityEntries, statisticsEntries);
            logger.info("Создан объект Root для XML и JSON");

            // Генерация XML
            XmlWriter.writeToXml(root);

            // Генерация JSON
            JsonWriter.writeToJson(root);

        } catch (IOException e) {
            logger.error("Ошибка при инициализации конфигурации логирования или записи в Excel", e);
        } catch (Exception e) {
            logger.error("Непредвиденная ошибка в приложении", e);
        }
    }
}