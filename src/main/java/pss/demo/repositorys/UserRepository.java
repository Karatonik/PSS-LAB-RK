package pss.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pss.demo.models.Role;
import pss.demo.models.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByName(String name);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);


    Optional<User> findByUserKey(String userKey);

    Set<User> findByRoleSetContaining(Role role);
}
