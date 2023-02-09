package ines.courseinfo.server;

import ines.courseinfo.domain.Course;
import ines.courseinfo.repository.CourseRepository;
import ines.courseinfo.repository.RepositoryException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Path("/courses")
public class CourseResource {
    public static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Course> getResources(){
        try {
            return courseRepository.getAll()
                    .stream()
                    .sorted(Comparator.comparing(Course::id));
        } catch (RepositoryException e) {
            LOG.error("Could not retrieve courses from DB", e);
            throw new NotFoundException();
        }
    }

    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNotes(@PathParam("id") String id, String notes) {
        courseRepository.addNotes(id, notes);
    }
}
