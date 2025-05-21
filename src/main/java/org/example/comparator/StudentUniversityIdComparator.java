package org.example.comparator;

import org.example.model.Student;
import org.apache.commons.lang3.StringUtils;

public class StudentUniversityIdComparator implements StudentComparator {
    @Override
    public int compare(Student s1, Student s2) {
        return StringUtils.compare(s1.getUniversityId(), s2.getUniversityId());
    }
}