package ines.courseinfo.server;

import ines.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.net.URI;


public class CourseServer {
    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);
    private static final String BASE_URI = "http://localhost:8080/";

    public static void main(String... args) {
//        String databaseFilename = loadDatabaseFilename();
        LOG.info("Starting HTTP server :)");
        CourseRepository courseRepository = CourseRepository.openCourseRepository("./courses.db");
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

}
