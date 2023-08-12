package lk.ijse.gdse.service.custom.impl;


import lk.ijse.gdse.dao.DaoFactory;
import lk.ijse.gdse.dao.custom.ItemDao;
import lk.ijse.gdse.dao.util.DaoTypes;
import lk.ijse.gdse.dto.ItemDto;
import lk.ijse.gdse.entity.Item;
import lk.ijse.gdse.service.custom.ItemService;
import lk.ijse.gdse.service.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemServiceImpl implements ItemService {
    private final ItemDao itemDao;

    public ItemServiceImpl() {
        itemDao = DaoFactory.getInstance().getDAO(DaoTypes.ITEM_DAO);
    }

    @Override
    public boolean save(ItemDto dto) {
        Item item = new Item();

        item.setCode(dto.getCode());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setQty(dto.getQty());

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            itemDao.save(item, session);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean update(ItemDto dto) {
        Item item = new Item();
        item.setCode(dto.getCode());
        item.setDescription(dto.getDescription());
        item.setPrice(dto.getPrice());
        item.setQty(dto.getQty());

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try (session) {
            itemDao.update(item, session);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemDto view(ItemDto dto) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Item item = new Item();
            item.setCode(dto.getCode());
            Item viewed = itemDao.view(item, session);
            if (viewed != null) {
                dto.setCode(viewed.getCode());
                dto.setDescription(viewed.getDescription());
                dto.setPrice(viewed.getPrice());
                dto.setQty(viewed.getQty());
                return dto;
            }
            throw new RuntimeException("Item not found");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(ItemDto dto) {
        Item item = new Item();
        item.setCode(dto.getCode());
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try (session) {
            itemDao.delete(item, session);
            transaction.commit();
            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
