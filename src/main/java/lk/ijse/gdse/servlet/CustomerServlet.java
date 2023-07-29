package lk.ijse.gdse.servlet;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse.dto.CustomerDto;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.regex.Pattern;
@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {


    Connection connection;
    String sql = "INSERT INTO customer(id,name,address,salary) VALUES (?,?,?,?)";

    @Override
    public void init() throws ServletException {

        try {
            InitialContext ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/student");
            this.connection = pool.getConnection();


        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do-post");
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            try {
                Jsonb jsonb = JsonbBuilder.create();
                CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,customerDto.getId());
                ps.setString(2,customerDto.getName());
                ps.setString(3,customerDto.getAddress());
                ps.setDouble(4,customerDto.getSalary());

                if(ps.executeUpdate() < 1){
                    System.out.println("failed");
                }
                ResultSet rst = ps.getGeneratedKeys();
                rst.next();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                //the created json is sent to frontend
                resp.setContentType("application/json");
                jsonb.toJson(customerDto,resp.getWriter());

            } catch (SQLException | RuntimeException e) {
                e.printStackTrace();
            }
        }
    }

}
