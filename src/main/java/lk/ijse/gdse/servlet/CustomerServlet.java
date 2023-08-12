package lk.ijse.gdse.servlet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import lk.ijse.gdse.dto.CustomerDto;
import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.CustomerService;
import lk.ijse.gdse.service.util.ServiceTypes;

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
    private CustomerService customerService;
    private Jsonb jsonb;

    @Override
    public void init() throws ServletException {
        customerService = ServiceFactory.getInstance().getService(ServiceTypes.CUSTOMER_SERVICE);
        jsonb = JsonbBuilder.create();
    }

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("do-get");
    if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    } else {
        try {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(req.getParameter("id"));
            CustomerDto view = customerService.view(customerDto);
            if (view != null) {
                System.out.println("Customer exists");
                String json = JsonbBuilder.create().toJson(view);  // Convert the customerDto to JSON using JSON-B (Yasson)
                resp.setContentType("application/json");
                PrintWriter writer = resp.getWriter();
                writer.print(json);
                writer.flush();
                writer.close();
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Customer is not exists");
            }
        } catch (RuntimeException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do-post");
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            try {
                CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);
                validate(customerDto);
                boolean save = customerService.save(customerDto);
                if (save) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    System.out.println("Data saved successfully.");
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to save data.");
                }
            } catch (RuntimeException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do-put");
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            try {
                CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);
                validate(customerDto);
                boolean update = customerService.update(customerDto);
                if (update) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    System.out.println("Data updated successfully.");
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update data.");
                }
            } catch (RuntimeException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do-delete");
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            try {
                CustomerDto customerDto = jsonb.fromJson(req.getReader(), CustomerDto.class);
                boolean delete = customerService.delete(customerDto);
                if (delete) {
                    resp.setStatus(HttpServletResponse.SC_OK); // 200 status code for success
                    System.out.println("Data deleted successfully.");
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete data.");
                }
            } catch (RuntimeException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            }
        }
    }

    private void validate(CustomerDto customerDto) throws RuntimeException {
        System.out.println(customerDto);
        if (customerDto.getId() == null || !customerDto.getId().matches("^C(00[1-9]|0[1-9]\\d|[1-9]\\d{2})$")) {
            throw new RuntimeException("Invalid customer id!");
        } else if (customerDto.getName() == null || !customerDto.getName().matches("^[A-Za-z]+(?:\\s[A-Za-z]+)*$")) {
            throw new RuntimeException("Invalid customer name!");
        } else if (customerDto.getAddress() == null || !customerDto.getAddress().matches("^[A-Za-z]+(?:\\s[A-Za-z]+)*$")) {
            throw new RuntimeException("Invalid customer city!");
        } else if (!Pattern.compile("^[0-9]+\\.?[0-9]*$").matcher(customerDto.getSalary().toString()).matches()) {
            throw new RuntimeException("Invalid customer salary!");
        }
    }
}
