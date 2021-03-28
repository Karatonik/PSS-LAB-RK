package pss.demo.services.interfaces;

import pss.demo.enums.ERole;
import pss.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface  RoleService {

    public Role  set(ERole roleName);

    public void delete(ERole roleName);

    public List<Role> getAll();

}
