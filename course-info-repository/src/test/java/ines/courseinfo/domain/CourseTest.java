package ines.courseinfo.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    Course validCourse = new Course("1", "name", 45, "url", Optional.empty());

    @Test
    void filled() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("", "name", 45, "url", Optional.empty()) );
    }
}