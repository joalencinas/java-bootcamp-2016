package practice4;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.bson.types.ObjectId;

public class StudentDAO extends BasicDAO<Student, ObjectId> {
    
    public StudentDAO(Morphia morphia, MongoClient mongo) {
        super(mongo, morphia, "highschool");
    }
}

