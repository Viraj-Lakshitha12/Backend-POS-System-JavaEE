package lk.ijse.gdse.servlet;


import lk.ijse.gdse.service.ServiceFactory;
import lk.ijse.gdse.service.custom.OrdersService;
import lk.ijse.gdse.service.util.ServiceTypes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class OrderServlet extends HttpServlet {
    private OrdersService ordersService;

    @Override
    public void init() throws ServletException {
        ordersService = ServiceFactory.getInstance().getService(ServiceTypes.ORDER_SERVICE);
    }
}
