package pss.demo.services.interfaces;

import pss.demo.models.Role;

import java.util.List;
import java.util.Set;

public interface  RoleService {

    public Role  set(String roleName);

    public void delete(String roleName);

    public List<Role> getAll();
}
