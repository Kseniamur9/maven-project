package org.example.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.Student;
import org.example.model.University;

import java.lang.reflect.Type;
import java.util.List;

public final class JsonUtil {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private JsonUtil() {
        throw new UnsupportedOperationException("Utility class, cannot be instantiated");
    }

    public static String serializeStudent(Student student) {
        return GSON.toJson(student);
    }

    public static String serializeUniversity(University university) {
        return GSON.toJson(university);
    }

    public static String serializeStudents(List<Student> students) {
        return GSON.toJson(students);
    }

    public static String serializeUniversities(List<University> universities) {
        return GSON.toJson(universities);
    }

    public static Student deserializeStudent(String json) {
        return GSON.fromJson(json, Student.class);
    }

    public static University deserializeUniversity(String json) {
        return GSON.fromJson(json, University.class);
    }

    public static List<Student> deserializeStudents(String json) {
        Type listType = new TypeToken<List<Student>>(){}.getType();
        return GSON.fromJson(json, listType);
    }

    public static List<University> deserializeUniversities(String json) {
        Type listType = new TypeToken<List<University>>(){}.getType();
        return GSON.fromJson(json, listType);
    }
}