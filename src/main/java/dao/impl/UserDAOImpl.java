package dao.impl;

import dao.UserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void insertUser(User user) {
        try {

            Session currentSession = sessionFactory.openSession();

            Transaction transaction = currentSession.beginTransaction();
            currentSession.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    @Override
    public User findUser(String userName, String password) {
        Session currentSession = sessionFactory.openSession();


        return (User) currentSession.createCriteria(User.class)
                .add(Restrictions.eq("userName", userName).ignoreCase())
                .add(Restrictions.eq("password", password)).uniqueResult();


    }


}
