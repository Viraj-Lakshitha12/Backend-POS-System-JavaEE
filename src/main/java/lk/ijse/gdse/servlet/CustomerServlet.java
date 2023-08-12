package lk.ijse.gdse.servlet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
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
    private final String sql = "INSERT INTO customer VALUES (?,?,?,?)";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/thogakade";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "1234";

//    private Connection connection ;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do-post");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection =  DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("connection oky");
            String contentType = req.getContentType();
            System.out.println(contentType);

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,customerDto.getId());
            ps.setString(2,customerDto.getName());
            ps.setString(3,customerDto.getAddress());
            ps.setDouble(4, customerDto.getSalary());
            int i = ps.executeUpdate();
            if (i>0){
                System.out.println("added oky");
            }else {
                System.out.println("added fail");
            }
        } catch (SQLException |ClassNotFoundException| RuntimeException e) {
            e.printStackTrace();
        }
//        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
//            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//
//        }
//
//            try {
//                Jsonb jsonb = JsonbBuilder.create();
//                CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);
//
//                PreparedStatement ps = connection.prepareStatement(sql);
//                ps.setString(1,customerDto.getId());
//                ps.setString(2,customerDto.getName());
//                ps.setString(3,customerDto.getAddress());
//                ps.setDouble(4,customerDto.getSalary());
//                System.out.println("set oky");
////                if(ps.executeUpdate() < 1){
////                    System.out.println("failed");
////                }
////
////                //the created json is sent to frontend
////                resp.setContentType("application/json");
////                jsonb.toJson(customerDto,resp.getWriter());
//
//            } catch (SQLException | RuntimeException e) {
//                System.out.println(e.getMessage());
//            }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("sdggf");
    }
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try {
//            Class.forName(getServletContext().getInitParameter("mysql-driver"));
//            String username = getServletContext().getInitParameter("db-user");
//            String password = getServletContext().getInitParameter("db-pw");
//            String url = getServletContext().getInitParameter("db-url");
//            this.connection = DriverManager.getConnection(url,username,password);
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        JsonReader reader = Json.createReader(req.getReader());
//        JsonObject jsonObject = reader.readObject();
//        String id = jsonObject.getString("id");
//        String name = jsonObject.getString("name");
//        String address = jsonObject.getString("address");
//        String salary = String.valueOf(jsonObject.getInt("salary"));
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE customer SET name = ? , address = ? , salary = ? WHERE id = ?");
//            preparedStatement.setObject(1,name);
//            preparedStatement.setObject(2,address);
//            preparedStatement.setObject(3,salary);
//            preparedStatement.setObject(4,id);
//            int i = preparedStatement.executeUpdate();
//            if (i>1){
//                System.out.println("updated");
//                PrintWriter writer = resp.getWriter();
//                writer.write("updated successfully");
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
    
}
