package pss.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pss.demo.models.Role;
import pss.demo.repositorys.RoleRepository;
import pss.demo.services.RoleServiceImp;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {


    RoleServiceImp roleServiceImp;
    @Autowired
    public RoleController(RoleServiceImp roleServiceImp) {
        this.roleServiceImp = roleServiceImp;
    }

    @PostMapping
    public Role create(String roleName){
        return roleServiceImp.set(roleName);
    }

    @DeleteMapping
    public void delete(String roleName) {
        roleServiceImp.delete(roleName);
    }
    @GetMapping
    public List<Role> getAll() {
        return roleServiceImp.getAll();
    }


}
