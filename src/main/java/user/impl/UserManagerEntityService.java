package user.impl;


import dao.impl.UserDAOImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.UserManager;

@Service
public class UserManagerEntityService implements UserManager {

    @Autowired
    private UserDAOImpl userDAO;


    @Override
    public void insert(User user) throws Exception {
        userDAO.insertUser(user);


    }

    @Override
    public User findUser(String userName, String password) throws Exception {
      return   userDAO.findUser(userName,password);
    }


}
