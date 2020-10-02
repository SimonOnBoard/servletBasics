package services;

import freemarker.core.ParseException;
import freemarker.template.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Helper {
    public static void render(HttpServletRequest request, HttpServletResponse response, String path, Map<String,Object> root){
        Configuration configuration = (Configuration) request.getServletContext().getAttribute("cfg");
        try {
            Template tmpl = configuration.getTemplate(path);
            tmpl.process(root, response.getWriter());
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
