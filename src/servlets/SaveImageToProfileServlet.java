package servlets;


import models.Student;
import repositories.StudentsDao;
import repositories.StudentsDaoImpl;
import services.FileSaver;
import services.FileSaverImpl;
import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@WebServlet("/saveImage")
@MultipartConfig
public class SaveImageToProfileServlet extends HttpServlet {
    private FileSaver fileSaver;
    private StudentsDao studentsDao;
    private Helper helper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "file_load.ftl", new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("userId");
        Part photoPart = req.getPart("photo");
        Optional<String> resultOfFileSaving = fileSaver.saveFile(photoPart, id);
        if (resultOfFileSaving.isPresent()) {
            Optional<Student> studentCandidate = studentsDao.find(Long.parseLong(id));
            if (studentCandidate.isPresent()) {
                Student student = studentCandidate.get();
                student.setImagePath(resultOfFileSaving.get());
                studentsDao.update(student);
                resp.sendRedirect("/profile?userid=" + id);
            } else {
                //поругаться на сохранение файла несуществующему пользователю
            }
        } else {
            //выдать сообщение что файл не удалось сохранить так как он пустой
        }
    }

    @Override
    public void init() throws ServletException {
        fileSaver = new FileSaverImpl(getServletContext().getRealPath(""));
        studentsDao = new StudentsDaoImpl();
        helper = new Helper();
    }
}
