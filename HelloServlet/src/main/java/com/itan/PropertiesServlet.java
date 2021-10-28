package com.itan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 使用ServletContext读取配置文件
 */
public class PropertiesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/jdbc.properties");
        Properties pro = new Properties();
        pro.load(is);
        String username = pro.getProperty("username");
        String password = pro.getProperty("password");
        resp.getWriter().print(username +": "+ password);

    }
}
