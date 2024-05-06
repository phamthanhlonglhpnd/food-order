package polarbear.java.foodorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import polarbear.java.foodorder.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}
