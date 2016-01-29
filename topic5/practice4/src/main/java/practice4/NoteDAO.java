package practice4;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.Datastore;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.ArrayList;

public class NoteDAO extends BasicDAO<Note, ObjectId> {
    
    public NoteDAO(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "highschool");
    }
    
    public List<Student> getStudentsThatScoredAtLeast4InGivenCourse(Course course) {
        Datastore datastore = super.getDatastore();
        Course matchingCourse = datastore.find(Course.class, "courseName", course.getCourseName()).get();
        List <Note> matchingNotes = datastore.find(Note.class, "finalNote >", 4).asList();
        List <Student> result = new ArrayList();
        for (int i = 0; i<matchingNotes.size(); i++) {
            Note note = matchingNotes.get(i);
            Student it = note.getStudent();
            result.add(it);
        }
        
        result.sort(null);
        return result;
    }
}

