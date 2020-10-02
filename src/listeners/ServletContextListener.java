package listeners;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println();
        ServletContext servletContext = servletContextEvent.getServletContext();
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setServletContextForTemplateLoading(servletContext, "/WEB-INF/templates");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        servletContext.setAttribute("cfg", cfg);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
