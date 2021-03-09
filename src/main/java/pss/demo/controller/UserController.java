package pss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.models.dto.UserDTO;
import pss.demo.services.UserServiceImp;


import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserServiceImp userServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }
    //a
    @PostMapping("/register")
    public void register(User user){
        userServiceImp.set(user);
    }
    //b
    @GetMapping
    public List<User> getAll(){
        return userServiceImp.getAll();
    }
    //c
    @PutMapping("/changePassword")
    public void changePassword(int userId, String newPassword){
        userServiceImp.changePassword(userId,newPassword);
    }
    //d
    @DeleteMapping("/deleteById")
    public void deleteById(int userId){
        userServiceImp.deleteById(userId);
    }
    //e
    @PostMapping("/setDelegation")
    public void setDelegation(int userId, Delegation delegation){
        userServiceImp.set(userId,delegation);
    }
//    //k
//    @GetMapping("/byRoleName")
//    public List<User> byRoleName(String roleName){
//        return userServiceImp.getAllByRoleName(roleName);
//    }
}
