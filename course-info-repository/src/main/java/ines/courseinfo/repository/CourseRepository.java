package ines.courseinfo.repository;

import ines.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course);
    List<Course> getAll();

    void addNotes(String id, String notes);

    static CourseRepository openCourseRepository(String dbFile){
        return new CourseJdbcRepository(dbFile);
    }
}
