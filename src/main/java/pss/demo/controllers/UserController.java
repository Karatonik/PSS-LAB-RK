package pss.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.services.UserServiceImp;

import java.util.List;
import java.util.Set;

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
    public void register(@RequestBody User user){
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
    @DeleteMapping
    public void deleteById(int userId){
        userServiceImp.deleteById(userId);
    }
    //e
    @PostMapping("/setDelegation")
    public void setDelegation( @RequestBody Delegation delegation,int userId){
        userServiceImp.set(userId,delegation);
    }
    //k
    @GetMapping("/byRoleName")
    public Set<User> byRoleName(String roleName){
        return userServiceImp.getAllByRoleName(roleName);
    }

    @PutMapping("/AddRole")
    public void addRole(String roleName,int userId ){
      userServiceImp.addRole(roleName,userId);
    }
}
