package servlets;

import services.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        helper = new Helper();
    }

    private Helper helper;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");


        // просмотр всех доступных параметров
//        Map<String, String[]> parameters = req.getParameterMap();
//        for (String string : parameters.keySet()) {
//            System.err.println("________________________");
//            System.err.println(string);
//            String[] strings = parameters.get(string);
//            System.err.println(strings.length);
//            for (int i = 0; i < strings.length; i++) {
//                System.err.println(strings[i]);
//            }
//        }

        //просмотр атрибутов запроса
//        Enumeration<String> attributes = req.getAttributeNames();
////        Iterator<String> i = attributes.asIterator();
////        while (i.hasNext()) {
////            System.err.println(i.next());
////        }
        resp.setContentType("text/html");


//        Enumeration<String> attributes1 = req.getAttributeNames();
//        Iterator<String> i1 = attributes1.asIterator();
//        while (i1.hasNext()) {
//            System.out.println(i1.next());
//        }

        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("name", name);
        objectMap.put("email", email);
        helper.render(req, resp, "hello.ftl", objectMap);
    }
}
