package pss.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Role {
    @Id
    String roleName;



@ManyToMany
Set<User> userSet;

    public Role(String roleName, Set<User> userSet) {
        this.roleName = roleName;
        this.userSet = userSet;
    }

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }
}
