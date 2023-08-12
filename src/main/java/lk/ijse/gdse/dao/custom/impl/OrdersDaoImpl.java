package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.OrdersDao;
import lk.ijse.gdse.entity.Orders;
import org.hibernate.Session;

public class OrdersDaoImpl implements OrdersDao {
    @Override
    public boolean save(Orders entity, Session session) {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Orders entity, Session session) {
        return false;
    }

    @Override
    public Orders view(Orders entity, Session session) {
        return null;
    }

    @Override
    public boolean delete(Orders entity, Session session) {
        return false;
    }
}
