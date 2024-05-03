package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import polarbear.java.foodorder.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}
