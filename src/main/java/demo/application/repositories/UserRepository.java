package demo.application.repositories;

import demo.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    public User findByName(String name);

    public User findByEmail(String email);

}
