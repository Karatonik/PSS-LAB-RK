package pss.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pss.demo.enums.ERole;
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
    public Role set(ERole roleName) {
        Role role = new Role(roleName);
     return    roleRepository.save(role);
    }

    @Override
    public void delete(ERole roleName) {
        Optional<Role> roleOptional = roleRepository.findById(String.valueOf(roleName));
        roleOptional.ifPresent(role -> roleRepository.delete(role));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

}
