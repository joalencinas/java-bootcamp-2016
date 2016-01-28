package practice4;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.bson.types.ObjectId;
import java.util.*;

public class NoteDAO extends BasicDAO<Note, ObjectId> {
    
    public NoteDAO(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "highschool");
    }
    
    public List<Student> getStudentsThatScoredAtLeast4InGivenCourse(Course course) {
        Datastore datastore = super.getDatastore();
        Course matchingCourse = datastore.find(Course.class, "courseName", course.getCourseName()).get();
        List <Student> result = datastore.find(Student.class, "finalNote >", 4).asList();
        
        result.sort(null);
        return result;
    }
}

