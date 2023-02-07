package ines.gameinfo.cli.service;
import ines.courseinfo.domain.Course;
import ines.courseinfo.repository.CourseRepository;
import java.util.List;

public class CourseStorageService {
    private static final String PS_BASE_URL = "https://app.pluralsight.com";
    private final CourseRepository courseRepository;
    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void storeAPICourses(List<APICourse> apiCourses) {
        for (APICourse apiCourse : apiCourses) {
            Course course = new Course(apiCourse.id(),
                    apiCourse.title(), apiCourse.durationInMinutes(),
                    PS_BASE_URL + apiCourse.contentUrl());
            courseRepository.saveCourse(course);
        }
    }

}