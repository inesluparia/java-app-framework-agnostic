package ines.courseinfo.domain;

//represents the core domain, the  CLI has a dependency on this module
public record Course(String id, String name, long length, String url ) {

    //records have compact constructor
    public Course {
        filled(id);
        filled(name);
        filled(url);
    }

    private static void filled(String s) {
        if (s == null || s.isBlank()){
            throw new IllegalArgumentException("Missing value for Course");
        }
    }
}
