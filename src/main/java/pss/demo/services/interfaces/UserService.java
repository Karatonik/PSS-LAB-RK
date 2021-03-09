package pss.demo.services.interfaces;

import pss.demo.models.Delegation;
import pss.demo.models.User;
import pss.demo.models.dto.UserDTO;

import java.util.List;

public interface UserService {
    //k
  //  List<User> getAllByRoleName(String roleName);
    //a
    void set(UserDTO userDTO);
    //b
    List<User> getAll();
    //c
    void changePassword(int userId, String newPassword);
    //d
    void deleteById(int userId);
    //e
    void set(int userId, Delegation delegation);
}
