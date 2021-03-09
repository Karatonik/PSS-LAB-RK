package pss.demo.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pss.demo.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
}
