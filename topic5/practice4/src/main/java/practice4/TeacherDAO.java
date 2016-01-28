package practice4;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.bson.types.ObjectId;
import java.util.*;

public class TeacherDAO extends BasicDAO<Teacher, ObjectId> {
    
    public TeacherDAO(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "highschool");
    }
    
    public List<Course> getAllCoursesForAGivenTeacher(Teacher teacher) {
        Datastore datastore = super.getDatastore();
        Teacher matchingTeacher = datastore.find(Teacher.class, "lastName", teacher.getLastName()).get();
        List <Course> result = matchingTeacher.getAssignedCourses();
        
        result.sort(null);
        return result;
    }
}

