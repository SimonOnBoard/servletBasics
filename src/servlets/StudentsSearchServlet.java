package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
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
import java.util.List;

@WebServlet("/search")
public class StudentsSearchServlet extends HttpServlet {
    private StudentsDao studentsDao;
    private ObjectMapper objectMapper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");

        List<Student> students = studentsDao.findAllStudentsWithNameLikePattern(query);

        String result = objectMapper.writeValueAsString(students);
        resp.getWriter().write(result);
    }

    @Override
    public void init() throws ServletException {
        studentsDao = new StudentsDaoImpl();
        objectMapper = new ObjectMapper();
    }
}
