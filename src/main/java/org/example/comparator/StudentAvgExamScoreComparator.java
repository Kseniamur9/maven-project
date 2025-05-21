package org.example.comparator;

import org.example.model.Student;

public class StudentAvgExamScoreComparator implements StudentComparator {
    @Override
    public int compare(Student s1, Student s2) {
        // Descending order for average exam score
        return Float.compare(s2.getAvgExamScore(), s1.getAvgExamScore());
    }
}
