package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.CustomerDao;
import lk.ijse.gdse.entity.Customer;
import org.hibernate.Session;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(Customer entity, Session session) throws RuntimeException {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Customer entity, Session session)throws RuntimeException  {
        session.update(entity);
        return true;
    }

    @Override
    public Customer view(Customer entity, Session session) throws RuntimeException{
        return session.get(Customer.class, entity.getId());
    }

    @Override
    public boolean delete(Customer entity, Session session) throws RuntimeException {
        session.delete(entity);
        return true;
    }
}
