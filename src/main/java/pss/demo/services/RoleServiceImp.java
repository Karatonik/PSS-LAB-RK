package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pss.demo.models.Role;
import pss.demo.repositorys.RoleRepository;
import pss.demo.services.interfaces.RoleService;

import java.util.List;
import java.util.Optional;
@Service
public class RoleServiceImp implements RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role set(Role role) {
     return    roleRepository.save(role);
    }

    @Override
    public void delete(String roleName) {
        Optional<Role> roleOptional = roleRepository.findById(roleName);
        roleOptional.ifPresent(role -> roleRepository.delete(role));
    }

    @Override
    public boolean rename(String roleName, String newRoleName) {
        Optional<Role> optionalRole = roleRepository.findById(roleName);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setRoleName(newRoleName);
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

}
