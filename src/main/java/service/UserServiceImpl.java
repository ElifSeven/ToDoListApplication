package service;

import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public void insertUser(User user) {
        this.userDAO.insertUser (user);
    }

    @Override
    public User findUser(String userName, String password) {

        return this.userDAO.findUser(userName,password);

    }


}
