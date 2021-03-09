package pss.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pss.demo.models.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //public List<User> findAllByRoleName(String roleName);


}
