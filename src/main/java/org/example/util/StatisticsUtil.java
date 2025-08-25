package org.example.util;

import org.example.model.Statistics;
import org.example.model.Student;
import org.example.model.StudyProfile;
import org.example.model.University;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

public class StatisticsUtil {
    private StatisticsUtil() {
        throw new UnsupportedOperationException("Utility class, cannot be instantiated");
    }

    public static List<Statistics> calculateStatistics(List<Student> students, List<University> universities) {
        return universities.stream()
                .map(University::getMainProfile)
                .distinct()
                .map(profile -> {
                    // Собираем университеты по профилю
                    Set<String> universityNames = universities.stream()
                            .filter(u -> u.getMainProfile() == profile)
                            .map(University::getFullName)
                            .collect(Collectors.toSet());
                    int universityCount = universityNames.size();

                    // Собираем студентов по профилю (через universityId)
                    List<Student> studentsByProfile = students.stream()
                            .filter(s -> universities.stream()
                                    .anyMatch(u -> u.getId().equals(s.getUniversityId()) && u.getMainProfile() == profile))
                            .collect(Collectors.toList());
                    int studentCount = studentsByProfile.size();

                    // Вычисляем средний балл
                    OptionalDouble avgExamScore = studentCount > 0
                            ? studentsByProfile.stream()
                            .mapToDouble(Student::getAvgExamScore)
                            .average()
                            : OptionalDouble.empty();

                    return new Statistics(profile, avgExamScore, studentCount, universityCount, universityNames);
                })
                .collect(Collectors.toList());
    }
}

