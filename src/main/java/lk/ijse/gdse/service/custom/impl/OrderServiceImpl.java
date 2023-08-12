package lk.ijse.gdse.service.custom.impl;


import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.OrdersDao;
import lk.ijse.gdse.dao.util.DaoTypes;
import lk.ijse.gdse.dto.OrdersDto;
import lk.ijse.gdse.service.custom.OrdersService;

public class OrderServiceImpl implements OrdersService {
    private final OrdersDao ordersDao;

    public OrderServiceImpl() {
        ordersDao = DaoFactory.getInstance().getDAO(DaoTypes.ORDERS_DAO);
    }

    @Override
    public boolean save(OrdersDto dto) {
        return false;
    }

    @Override
    public boolean update(OrdersDto dto) {
        return false;
    }

    @Override
    public OrdersDto view(OrdersDto dto) {
        return null;
    }

    @Override
    public boolean delete(OrdersDto dto) {
        return false;
    }
}
