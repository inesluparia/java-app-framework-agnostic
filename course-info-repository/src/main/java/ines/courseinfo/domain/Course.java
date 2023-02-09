package ines.courseinfo.domain;

import java.util.Optional;

//represents the core domain, the  CLI has a dependency on this module
public record Course(String id, String name, long length, String url, Optional<String> notes ) {

    //records have compact constructor
    public Course {
        filled(id);
        filled(name);
        filled(url);
        notes.ifPresent(Course::filled);
    }

    private static void filled(String s) {
        if (s == null || s.isBlank()){
            throw new IllegalArgumentException("Missing value for Course");
        }
    }
}
