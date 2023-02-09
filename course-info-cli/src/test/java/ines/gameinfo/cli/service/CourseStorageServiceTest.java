package ines.gameinfo.cli.service;

import ines.courseinfo.domain.Course;
import ines.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import java.util.List;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    List<APICourse> apiCourses;
    @Test
    void storeAPICourses() {
        InMemoryCourseRepository dummyRepo = new InMemoryCourseRepository();
        CourseStorageService courseStorageService = new CourseStorageService(dummyRepo);
        APICourse course1 = new APICourse("11", "Title", "00:34:00",
                "/blabla", false);

        courseStorageService.storeAPICourses(List.of(course1));

        Course expected = new Course("11", "Title", 34,
                "https://app.pluralsight.com/blabla", Optional.empty());
        assertEquals(List.of(expected), dummyRepo.getAll());
    };

    static class InMemoryCourseRepository implements CourseRepository {

        private final List<Course> courses = new ArrayList<>();

        @Override
        public void saveCourse(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAll() {
            return courses;
        }

        @Override
        public void addNotes(String id, String notes){
            throw new UnsupportedOperationException();
        }
    }
}