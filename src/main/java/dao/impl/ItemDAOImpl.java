package dao.impl;

import dao.ItemDAO;
import model.Item;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public class ItemDAOImpl implements ItemDAO {

    @Autowired
    SessionFactory sessionFactory;

private Item item;
    @Override
    public void insertItem(Item item) {

        try {

            Session currentSession = sessionFactory.openSession();

            Transaction transaction = currentSession.beginTransaction();
            currentSession.save(item);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @Override
    public Item findItemList(int userId) {


        try {
            Session currentSession = sessionFactory.openSession();

            Transaction transaction = currentSession.beginTransaction();
            transaction.commit();
            Criteria criteria = currentSession.createCriteria(Item.class);

            List<Item> list = criteria.list();
            for (Item item1 : list) {
                System.out.println("ID=" + item1.getUserId() + ", Adı=" + item1.getItemName());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }
}
