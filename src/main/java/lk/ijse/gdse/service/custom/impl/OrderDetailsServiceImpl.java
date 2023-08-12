package lk.ijse.gdse.service.custom.impl;

import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.OrderDetailsDao;
import lk.ijse.gdse.dao.util.DaoTypes;
import lk.ijse.gdse.dto.OrderDetailsDto;
import lk.ijse.gdse.service.custom.OrderDetailsService;

public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsDao orderDetailsDao;

    public OrderDetailsServiceImpl() {
        orderDetailsDao = DaoFactory.getInstance().getDAO(DaoTypes.ORDER_DETAILS_DAO);
    }

    @Override
    public boolean save(OrderDetailsDto dto) {
        return false;
    }

    @Override
    public boolean update(OrderDetailsDto dto) {
        return false;
    }

    @Override
    public OrderDetailsDto view(OrderDetailsDto dto) {
        return null;
    }

    @Override
    public boolean delete(OrderDetailsDto dto) {
        return false;
    }
}
