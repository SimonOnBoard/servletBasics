package servlets;

import models.User;
import services.Helper;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private Helper helper;
    private LoginService loginService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        helper.render(req, resp, "login.ftl",new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //сервис для проверки данных польозвателя
        boolean result = loginService.login(username, password);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        if(result){
            root.put("name", username);
            helper.render(req, resp, "profile.ftl", root);
        }else{
            root.put("message","incorrect password");
            helper.render(req, resp, "login.ftl", root);
        }
    }

    @Override
    public void init() throws ServletException {
        helper = new Helper();
        loginService = new LoginService();
    }
}
