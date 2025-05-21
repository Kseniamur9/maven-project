package org.example.util;

import org.example.comparator.*;
import org.example.model.Student;
import org.example.model.University;

public final class ComparatorUtil {
    private ComparatorUtil() {
    }

    public static StudentComparator getStudentComparator(StudentComparatorType type) {
        switch (type) {
            case FULL_NAME:
                return new StudentFullNameComparator();
            case UNIVERSITY_ID:
                return new StudentUniversityIdComparator();
            case COURSE_NUMBER:
                return new StudentCourseNumberComparator();
            case AVG_EXAM_SCORE:
                return new StudentAvgExamScoreComparator();
            default:
                throw new IllegalArgumentException("Unknown comparator type: " + type);
        }
    }

    public static UniversityComparator getUniversityComparator(UniversityComparatorType type) {
        switch (type) {
            case ID:
                return new UniversityIdComparator();
            case FULL_NAME:
                return new UniversityFullNameComparator();
            case SHORT_NAME:
                return new UniversityShortNameComparator();
            case YEAR_OF_FOUNDATION:
                return new UniversityYearOfFoundationComparator();
            case MAIN_PROFILE:
                return new UniversityMainProfileComparator();
            default:
                throw new IllegalArgumentException("Unknown comparator type: " + type);
        }
    }
}
