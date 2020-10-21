import models.Student;
import repositories.StudentsDao;
import repositories.StudentsDaoImpl;

import javax.swing.text.html.Option;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainTestHelpers {
    //    public static void main(String[] args) throws URISyntaxException, IOException {
//        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//            Desktop.getDesktop().browse(new URI("http://www.google.com"));
//        }
//
//
////        StudentsDao dao = new StudentsDaoImpl();
////        Student student = new Student(20,3,"Simon","Konkov",true);
////        Student student1 = new Student(20, 3, "Renat","Blinov",false);
////
////        dao.save(student);
////        dao.save(student1);
////
////        Optional<Student> studentCandidate = dao.find(student.getId());
////        if(studentCandidate.isPresent()){
////            Student student2 = studentCandidate.get();
////            System.out.println(student2);
////        }
////        else{
////            throw new IllegalStateException("Candidate not found");
////        }
////
////        List<Student> students = dao.findAll();
////        System.out.println(Arrays.toString(students.toArray()));
////
////
////        List<Student> studentList = dao.findAllStudentsWithNameLikePattern("nat");
////        System.out.println(Arrays.asList(studentList.toArray()));
////
////
////        System.out.println(student);
////        System.out.println(student1);
//    }
}
