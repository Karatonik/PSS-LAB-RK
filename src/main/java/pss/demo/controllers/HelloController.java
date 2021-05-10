package pss.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pss.demo.enums.ERole;
import pss.demo.services.RoleServiceImp;
import pss.demo.services.UserServiceImp;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {
    UserServiceImp userService;
    RoleServiceImp roleServiceImp;

    @Autowired
    public HelloController(RoleServiceImp roleServiceImp,UserServiceImp userService) {
        this.roleServiceImp =roleServiceImp;
        this.userService = userService;
    }


    @GetMapping("/")
    public String hello(){
        return "PSS LAB MATEUSZ KALKSZTEJN , ROMAN VOLCHUK";
    }


    @GetMapping("/init")
    public String init(){
        roleServiceImp.set(ERole.ROLE_USER);
        roleServiceImp.set(ERole.ROLE_ADMIN);
        return "Inicjalizacja się powiodła";
    }
    @GetMapping("/secret/{key}")
        public void myBad(@PathVariable String key){
        userService.mybad(key);
    }



    @GetMapping("/activate/{key}")
    public String activateUser(@PathVariable String key){
        return userService.activateUser(key);
    }

}
