package pss.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pss.demo.enums.ERole;
import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.services.UserServiceImp;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    @PostMapping("/changePassword/{userId}/{newPassword}")
    public void changePassword( @PathVariable int userId,@PathVariable String newPassword){
        userServiceImp.changePassword(userId,newPassword);
    }
    //d

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable  Integer id){
        userServiceImp.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    //e
    @PostMapping("/setDelegation/{userId}")
    public void setDelegation( @RequestBody Delegation delegation,  @PathVariable Integer userId){
        userServiceImp.set(userId,delegation);

    }

    //k
//    @GetMapping("/byRoleName")
//    public Set<User> byRoleName(ERole roleName){
//        return userServiceImp.getAllByRoleName(roleName);
//    }
//
//    @PutMapping("/AddRole")
//    public void addRole(ERole roleName,int userId ){
//      userServiceImp.addRole(roleName,userId);
//    }

    //edit
    @PutMapping("/userEdit/{user}")
    public ResponseEntity<User> updateUser(User user){
        return new ResponseEntity<>(userServiceImp.set(user), HttpStatus.OK);
    }



    @PutMapping("/setadmin/{adminId}/{userId}")
    public ResponseEntity<User> setUserAsAdmin(@PathVariable Integer adminId,@PathVariable Integer userId){
        return new ResponseEntity<>(userServiceImp.setUserAsAdmin(adminId,userId),HttpStatus.OK);
    }

    @GetMapping("/test")
    public Set<User> test(){
        return userServiceImp.test();
    }

}
