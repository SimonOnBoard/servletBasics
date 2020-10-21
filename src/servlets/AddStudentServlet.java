package servlets;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.StudentFormData;
import models.Student;
import repositories.StudentsDao;
import repositories.StudentsDaoImpl;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private Helper helper;
    private StudentsDao studentsDao;
    private ObjectMapper objectMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "addStudentPage.ftl", new HashMap<>());
    }


    @lombok.SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String test = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        StudentFormData studentFormData = objectMapper.readValue(test, StudentFormData.class);
        Student student = Student.from(studentFormData);
        studentsDao.save(student);
        resp.setStatus(200);
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put("redirectUrl","/searchPage");
        resp.getWriter().write(objectMapper.writeValueAsString(resultMap));
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        studentsDao = new StudentsDaoImpl();
        objectMapper = new ObjectMapper();
    }
}
