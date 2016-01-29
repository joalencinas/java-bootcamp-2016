package practice4;


import org.mongodb.morphia.Morphia;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import java.util.List;
import java.util.ArrayList;

public class Main {
    
    public static void main( String[] args ) throws Exception {
        MongoClient mongo = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();
        Datastore datastore = morphia.createDatastore(mongo, "highschool");
        morphia.mapPackage("practice4");
        
        //Create DAOs
        
        StudentDAO studentDAO = new StudentDAO(morphia, mongo);
        CourseDAO courseDAO = new CourseDAO(morphia, mongo);
        TeacherDAO teacherDAO = new TeacherDAO(morphia, mongo);
        NoteDAO noteDAO = new NoteDAO(morphia, mongo);
        
        //Load initial values to database, this should be executed just once,
        //because adding twice the same entry to the database duplicates such entry
        
        
        //Courses
        
        Course course1 = new Course();
        course1.setCourseName("Algorithms and Data Structures");
        course1.setHoursPerWeek(8);
        List<String> schedule1 = new ArrayList();
        schedule1.add(0,"Wednesday 09:00 - 13:00");
        schedule1.add(0,"Monday 09:00 - 13:00");
        course1.setSchedule(schedule1);
        courseDAO.save(course1);
        
        Course course2 = new Course();
        course2.setCourseName("Number Theory");
        course2.setHoursPerWeek(6);
        List<String> schedule2 = new ArrayList();
        schedule2.add(0,"Thursday 09:00 - 11:00");
        schedule2.add(0,"Tuesday 09:00 - 13:00");
        course2.setSchedule(schedule2);
        courseDAO.save(course2);
        
        Course course3 = new Course();
        course3.setCourseName("Cryptography");
        course3.setHoursPerWeek(6);
        List<String> schedule3 = new ArrayList();
        schedule3.add(0,"Friday 09:00 - 13:00");
        schedule3.add(0,"Thursday 11:00 - 13:00");
        course3.setSchedule(schedule3);
        courseDAO.save(course3);
        
        
        //Teachers
        
        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Edsger");
        teacher1.setLastName("Dijkstra");
        teacher1.setDateOfBirth("1930-05-11");
        List<Course> coursesList1 = new ArrayList();
        coursesList1.add(course1);
        coursesList1.add(course2);
        teacher1.setAssignedCourses(coursesList1);
        teacherDAO.save(teacher1);
        
        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("George");
        teacher2.setLastName("Boole");
        teacher2.setDateOfBirth("1815-11-02");
        List<Course> coursesList2 = new ArrayList();
        coursesList2.add(course2);
        teacher2.setAssignedCourses(coursesList2);
        teacherDAO.save(teacher2);
        
        Teacher teacher3 = new Teacher();
        teacher3.setFirstName("Alan");
        teacher3.setLastName("Turing");
        teacher3.setDateOfBirth("1912-06-23");
        List<Course> coursesList3 = new ArrayList();
        coursesList3.add(course3);
        teacher3.setAssignedCourses(coursesList3);
        teacherDAO.save(teacher3);
        
        
        //Students
        
        Student student1 = new Student();
        student1.setFirstName("El Pato");
        student1.setLastName("Donald");
        student1.setDateOfBirth("1934-06-09");
        studentDAO.save(student1);
        
        Student student2 = new Student();
        student2.setFirstName("Carlos");
        student2.setLastName("Bilardo");
        student2.setDateOfBirth("1939-03-19");
        studentDAO.save(student2);
        
        Student student3 = new Student();
        student3.setFirstName("Roger");
        student3.setLastName("Federer");
        student3.setDateOfBirth("1981-08-08");
        studentDAO.save(student3);
        
        Student student4 = new Student();
        student4.setFirstName("Juan Martin");
        student4.setLastName("Del Potro");
        student4.setDateOfBirth("1988-09-23");
        studentDAO.save(student4);
        
        Student student5 = new Student();
        student5.setFirstName("Oscar");
        student5.setLastName("Gomez");
        student5.setDateOfBirth("1982-10-11");
        studentDAO.save(student5);
        
        Student student6 = new Student();
        student6.setFirstName("Diego Armando");
        student6.setLastName("Maradona");
        student6.setDateOfBirth("1960-10-30");
        studentDAO.save(student6);
        
        Student student7 = new Student();
        student7.setFirstName("Pablo");
        student7.setLastName("Marmol");
        student7.setDateOfBirth("1932-12-14");
        studentDAO.save(student7);
        
        Student student8 = new Student();
        student8.setFirstName("Carlos");
        student8.setLastName("Gonzalez");
        student8.setDateOfBirth("1992-06-13");
        studentDAO.save(student8);
        
        Student student9 = new Student();
        student9.setFirstName("Camilo");
        student9.setLastName("Lopez");
        student9.setDateOfBirth("1996-09-22");
        studentDAO.save(student9);
        
        Student student10 = new Student();
        student10.setFirstName("Gonzalo");
        student10.setLastName("Ruiz");
        student10.setDateOfBirth("1995-01-14");
        studentDAO.save(student10);
        
        Student student11 = new Student();
        student11.setFirstName("Tomas");
        student11.setLastName("Rodriguez");
        student11.setDateOfBirth("1991-09-09");
        studentDAO.save(student11);
        
        Student student12 = new Student();
        student12.setFirstName("Marcos");
        student12.setLastName("Torrejon");
        student12.setDateOfBirth("1978-11-22");
        studentDAO.save(student12);
        
        
        //Notes
        
        Note note1 = new Note();
        note1.setCourse(course1);
        note1.setStudent(student1);
        note1.setFirstNote(6);
        note1.setSecondNote(7);
        note1.setThirdNote(8);
        note1.setFinalNote(7.0d);
        noteDAO.save(note1);
        
        Note note2 = new Note();
        note2.setCourse(course1);
        note2.setStudent(student2);
        note2.setFirstNote(5);
        note2.setSecondNote(9);
        note2.setThirdNote(9);
        note2.setFinalNote(7.66d);
        noteDAO.save(note2);
        
        Note note3 = new Note();
        note3.setCourse(course1);
        note3.setStudent(student3);
        note3.setFirstNote(7);
        note3.setSecondNote(7);
        note3.setThirdNote(7);
        note3.setFinalNote(7.0d);
        noteDAO.save(note3);
        
        Note note4 = new Note();
        note4.setCourse(course1);
        note4.setStudent(student4);
        note4.setFirstNote(2);
        note4.setSecondNote(2);
        note4.setThirdNote(2);
        note4.setFinalNote(2.0d);
        noteDAO.save(note4);
        
        Note note5 = new Note();
        note5.setCourse(course2);
        note5.setStudent(student5);
        note5.setFirstNote(3);
        note5.setSecondNote(4);
        note5.setThirdNote(5);
        note5.setFinalNote(4.0d);
        noteDAO.save(note5);
        
        Note note6 = new Note();
        note6.setCourse(course2);
        note6.setStudent(student6);
        note6.setFirstNote(10);
        note6.setSecondNote(10);
        note6.setThirdNote(10);
        note6.setFinalNote(10.0d);
        noteDAO.save(note6);
        
        Note note7 = new Note();
        note7.setCourse(course2);
        note7.setStudent(student7);
        note7.setFirstNote(2);
        note7.setSecondNote(7);
        note7.setThirdNote(2);
        note7.setFinalNote(3.66d);
        noteDAO.save(note7);
        
        Note note8 = new Note();
        note8.setCourse(course2);
        note8.setStudent(student8);
        note8.setFirstNote(2);
        note8.setSecondNote(9);
        note8.setThirdNote(8);
        note8.setFinalNote(6.33d);
        noteDAO.save(note8);
        
        Note note9 = new Note();
        note9.setCourse(course3);
        note9.setStudent(student9);
        note9.setFirstNote(3);
        note9.setSecondNote(7);
        note9.setThirdNote(1);
        note9.setFinalNote(3.66d);
        noteDAO.save(note9);
        
        Note note10 = new Note();
        note10.setCourse(course3);
        note10.setStudent(student10);
        note10.setFirstNote(3);
        note10.setSecondNote(9);
        note10.setThirdNote(10);
        note10.setFinalNote(7.33d);
        noteDAO.save(note10);
        
        Note note11 = new Note();
        note11.setCourse(course3);
        note11.setStudent(student11);
        note11.setFirstNote(10);
        note11.setSecondNote(7);
        note11.setThirdNote(4);
        note11.setFinalNote(7.0d);
        noteDAO.save(note11);
        
        Note note12 = new Note();
        note12.setCourse(course3);
        note12.setStudent(student12);
        note12.setFirstNote(2);
        note12.setSecondNote(2);
        note12.setThirdNote(2);
        note12.setFinalNote(2.0d);
        noteDAO.save(note12);
        
        
        //Practice 2
        
        List<Student> resultList = new ArrayList();
        resultList = noteDAO.getStudentsThatScoredAtLeast4InGivenCourse(course1);
        
        System.out.println("Students that scored at least 4 in a given course:\n");
        for (int i = 0; i < resultList.size(); i++) {
            Student it = resultList.get(i);
            System.out.printf("\t%s, %s\n", it.getLastName(), it.getFirstName());
        }
        System.out.printf("\n\n");
        
        
        //Practice 3
        
        List<Course> dijkstraCourses = new ArrayList();
        
        dijkstraCourses = teacherDAO.getAllCoursesForAGivenTeacher(teacher1);
        
        System.out.printf("\n\nCourses given by %s %s:\n\n", teacher1.getFirstName(),
            teacher1.getLastName());
        for (int i = 0; i < dijkstraCourses.size(); i++) {
            Course it = dijkstraCourses.get(i);
            System.out.printf("\n\t%s\n", it.getCourseName());
        }
        System.out.printf("\n\n");
        
        
        /*
            HERE goes some code to close the connection, because it keeps throwing
            IllegalThreadStateException.
        */
        return;
    }
}
