package lk.ijse.gdse.dao;


import lk.ijse.gdse.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse.dao.custom.impl.ItemDaoImpl;
import lk.ijse.gdse.dao.custom.impl.OrderDetailsDaoImpl;
import lk.ijse.gdse.dao.custom.impl.OrdersDaoImpl;
import lk.ijse.gdse.dao.util.DaoTypes;

public class DaoFactory {
    private static DaoFactory instance;

    public synchronized static DaoFactory getInstance() {
        return instance == null ? instance = new DaoFactory() : instance;
    }

    public <T extends SuperDao> T getDAO(DaoTypes type) {

        switch (type) {
            case CUSTOMER_DAO:
                return (T) new CustomerDaoImpl();
            case ITEM_DAO:
                return (T) new ItemDaoImpl();
            case ORDERS_DAO:
                return (T) new OrdersDaoImpl();
            case ORDER_DETAILS_DAO:
                return (T) new OrderDetailsDaoImpl();
            default:
                throw new RuntimeException("Error: Invalid DAO Type");
        }
    }
}
