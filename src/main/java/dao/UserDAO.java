package dao;

import model.User;

public interface UserDAO {

    void insertUser(User user);

     User findUser(String userName, String password);




}


