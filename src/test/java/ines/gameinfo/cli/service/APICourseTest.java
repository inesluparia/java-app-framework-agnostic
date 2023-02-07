package ines.gameinfo.cli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class APICourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            00:45:30, 45
            01:05:30, 65
            00:00:00, 0
            00:00:59.9999, 0
            """)
    void durationInMinutes(String input, int expected) {
        APICourse course =
                new APICourse("1", "Title", input,"url", false );
        assertEquals(expected, course.durationInMinutes());
    }

//    @Test
//    void durationInMinutes() {
//        APICourse course =
//                new APICourse("1", "Title", "00:45:30","url", false );
//        assertEquals(45, course.durationInMinutes());
//    }
//    @Test
//    void durationInMinutesPastHour() {
//        APICourse course =
//                new APICourse("1", "Title", "01:05:30","url", false );
//        assertEquals(65, course.durationInMinutes());
//    }
//    @Test
//    void durationInMinutesEdgeCase() {
//        APICourse course =
//                new APICourse("1", "Title", "00:00:59.9999","url", false );
//        assertEquals(0, course.durationInMinutes());
//    }
}