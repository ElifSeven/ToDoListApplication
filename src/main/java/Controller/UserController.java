package Controller;

import dao.impl.UserDAOImpl;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserService;

@Controller
public class UserController implements UserService {

    @Autowired
    UserDAOImpl userDAO;


    @Override
    @RequestMapping(value = "/user/insertUser", method = RequestMethod.POST)
    public void insertUser(User user) {

        if (user.getUserId() == 0) {
            this.userDAO.insertUser(user);
        } else {
            this.userDAO.insertUser(user);
        }

    }

    @Override
    public User findUser(String userName, String password) {

        return userDAO.findUser(userName, password);

    }
}
