package pss.demo.services.interfaces;

import pss.demo.enums.ERole;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    //k
//    Set<User> getAllByRoleName(ERole roleName);

    //a
    User set(User user);

    //b
    List<User> getAll();

    //c
    void changePassword(int userId, String newPassword);

    //d
    void deleteById(int userId);

    //e
    void set(int userId, Delegation delegation);


//    public void addRole(ERole roleName, int userId);

    public User get(int userId);

    User setUserAsAdmin(int adminId, int userId);

    public String activateUser(String key);

}
