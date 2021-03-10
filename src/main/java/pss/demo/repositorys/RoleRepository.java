package pss.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pss.demo.models.Role;
import pss.demo.models.User;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
}
