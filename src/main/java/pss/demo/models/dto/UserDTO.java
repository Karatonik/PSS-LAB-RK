package pss.demo.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import pss.demo.models.Delegation;
import pss.demo.models.Role;
import pss.demo.models.User;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

public class UserDTO {
    Integer userId;

    String companyName;

    String companyAddress;

    String companyNip;

    String name;

    String lastName;

    String email;

    String password;

    boolean status;

    Date registrationDate;
    @JsonIgnore
    Set<Role> roleSet;
    @JsonIgnore
    Set<Delegation> delegationSet;

    public UserDTO(Integer userId, String companyName, String companyAddress, String companyNip, String name, String lastName, String email, String password, boolean status, Date registrationDate, Set<Role> roleSet, Set<Delegation> delegationSet) {
        this.userId = userId;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyNip = companyNip;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.registrationDate = registrationDate;
        this.roleSet = roleSet;
        this.delegationSet = delegationSet;
    }
    public UserDTO(User user){
        this.userId = user.getUserId();
        this.companyName = user.getCompanyName();
        this.companyAddress = user.getCompanyAddress();
        this.companyNip = user.getCompanyNip();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.status = user.isStatus();
        this.registrationDate = user.getRegistrationDate();
        this.roleSet = user.getRoleSet();
        this.delegationSet = user.getDelegationSet();
    }
}
