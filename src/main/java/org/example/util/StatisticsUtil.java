package org.example.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.Statistics;
import org.example.model.Student;
import org.example.model.StudyProfile;
import org.example.model.University;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

public class StatisticsUtil {
    private static final Logger logger = LogManager.getLogger(StatisticsUtil.class.getName());

    private StatisticsUtil() {
        throw new UnsupportedOperationException("Класс-утилита, не может быть создан");
    }

    public static List<Statistics> calculateStatistics(List<Student> students, List<University> universities) {
        logger.info("Начало вычисления статистики для {} университетов и {} студентов", universities.size(), students.size());
        List<Statistics> statistics = universities.stream()
                .map(University::getMainProfile)
                .distinct()
                .map(profile -> {
                    Set<String> universityNames = universities.stream()
                            .filter(u -> u.getMainProfile() == profile)
                            .map(University::getFullName)
                            .collect(Collectors.toSet());
                    int universityCount = universityNames.size();

                    List<Student> studentsByProfile = students.stream()
                            .filter(s -> universities.stream()
                                    .anyMatch(u -> u.getId().equals(s.getUniversityId()) && u.getMainProfile() == profile))
                            .collect(Collectors.toList());
                    int studentCount = studentsByProfile.size();

                    OptionalDouble avgExamScore = studentCount > 0
                            ? studentsByProfile.stream()
                            .mapToDouble(Student::getAvgExamScore)
                            .average()
                            : OptionalDouble.empty();

                    return new Statistics(profile, avgExamScore, studentCount, universityCount, universityNames);
                })
                .collect(Collectors.toList());
        logger.info("Статистика вычислена для {} профилей", statistics.size());
        return statistics;
    }
}