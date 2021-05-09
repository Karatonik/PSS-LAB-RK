package pss.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pss.demo.services.UserServiceImp;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class HelloController {
    UserServiceImp userService;

    @Autowired
    public HelloController(UserServiceImp userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String hello(){
        return "PSS LAB MATEUSZ KALKSZTEJN , ROMAN VOLCHUK";
    }



    @GetMapping("/activate/{key}")
    public String activateUser(@PathVariable String key){
        return userService.activateUser(key);
    }

}
