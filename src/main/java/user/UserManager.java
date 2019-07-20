package user;

import model.User;

public interface UserManager {

    void insert(User user) throws Exception;
    User findUser(String userName, String password) throws Exception;




}
