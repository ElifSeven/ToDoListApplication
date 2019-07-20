package service;

import model.User;

public interface UserService {

    public void insertUser(User user);
    public User findUser(String userName, String password);
}
