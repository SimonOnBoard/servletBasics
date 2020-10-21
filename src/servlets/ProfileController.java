package servlets;

import models.Student;
import repositories.StudentsDao;
import repositories.StudentsDaoImpl;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileController extends HttpServlet {
    private StudentsDao studentsDao;
    private Helper helper;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdCandidate = req.getParameter("userid");
        if(userIdCandidate == null) throw new IllegalArgumentException("User id not exists");
        Optional<Student> studentCandidate = studentsDao.find(Long.parseLong(userIdCandidate));
        if(studentCandidate.isPresent()){
            Map<String, Object> attributesMap = new HashMap<>();
            attributesMap.put("user",studentCandidate.get());
            helper.render(req, resp, "profile.ftl",attributesMap);
        }
        else{
            throw new IllegalArgumentException("User with this id not found");
        }
    }

    @Override
    public void init() throws ServletException {
        studentsDao = new StudentsDaoImpl();
        helper = new Helper();
    }
}
