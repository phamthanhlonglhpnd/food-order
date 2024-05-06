package polarbear.java.foodorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import polarbear.java.foodorder.config.JwtProvider;
import polarbear.java.foodorder.model.User;
import polarbear.java.foodorder.repository.UserRepository;
import polarbear.java.foodorder.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("user not found");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
