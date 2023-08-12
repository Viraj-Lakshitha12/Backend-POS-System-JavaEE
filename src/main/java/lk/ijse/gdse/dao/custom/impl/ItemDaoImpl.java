package lk.ijse.gdse.dao.custom.impl;

import lk.ijse.gdse.dao.custom.ItemDao;
import lk.ijse.gdse.entity.Item;
import org.hibernate.Session;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(Item entity, Session session) throws  RuntimeException {
        session.save(entity);
        return true;
    }

    @Override
    public boolean update(Item entity, Session session)throws RuntimeException {
        session.update(entity);
        return true;
    }

    @Override
    public Item view(Item entity, Session session) {
        return session.get(Item.class, entity.getCode());
    }

    @Override
    public boolean delete(Item entity, Session session)throws RuntimeException  {
        session.delete(entity);
        return true;
    }
}
