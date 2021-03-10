package pss.demo.services.interfaces;

import pss.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface  RoleService {

    public Role  set(Role role);

    public void delete(String roleName);

    public boolean rename(String roleName, String newRoleName);

    public List<Role> getAll();
}
