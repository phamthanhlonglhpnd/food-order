package polarbear.java.foodorder.service;

import polarbear.java.foodorder.model.User;

import java.util.List;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public List<User> getAllUsers();
}
