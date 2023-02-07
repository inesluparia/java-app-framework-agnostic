package ines.courseinfo.repository;

import ines.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course);
    List<Course> getAll();

    static CourseRepository openCourseRepository(String dbFile){
        return new CourseJdbcRepository(dbFile);
    }
}
