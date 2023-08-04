package lk.ijse.gdse.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*") // Add the @WebFilter annotation and specify the URL pattern
public class CORSFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println("CORS filter");
        String origin = req.getHeader("Origin");
        if (origin != null && origin.contains("http://localhost:")) {
            System.out.println("CORS filter accepted");
            res.setHeader("Access-Control-Allow-Origin", origin);
            res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, HEAD");
            res.setHeader("Access-Control-Allow-Headers", "Content-Type");
            res.setHeader("Access-Control-Expose-Headers", "Content-Type");
            res.setHeader("Access-Control-Allow-Credentials", "true");
        }
        chain.doFilter(req, res);
    }

}
