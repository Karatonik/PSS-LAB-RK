package pss.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    @NotBlank(message ="companyName cannot be blank " )
    String companyName;

    @NotBlank(message ="companyAddress cannot be blank " )
    String companyAddress;

    @NotBlank(message ="companyNip cannot be blank " )
        @Pattern(regexp="^[0-9]{10}$\n",message ="incorrect companyNip" )
    String companyNip;

    @NotBlank(message ="name cannot be blank " )
    String name;
    @NotBlank(message ="lastName cannot be blank " )
    String lastName;

    @NotBlank(message ="email cannot be blank " )
    @Pattern(regexp="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message ="incorrect email" )
    String email;


    @NotBlank(message ="password cannot be blank " )
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$\"",message ="incorrect password" )
    //Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character
    String password;


    @Column(columnDefinition = "boolean default true")
    boolean status;

    @CreationTimestamp
    @Column(updatable = false)
        Date registrationDate;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roleSet = new HashSet<>();


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Delegation> delegationSet;


    public User() {
    }

    public User(Integer userId, @NotBlank(message = "companyName cannot be blank ") String companyName, @NotBlank(message = "companyAddress cannot be blank ") String companyAddress, @NotBlank(message = "companyNip cannot be blank ") @Pattern(regexp = "^[0-9]{10}$\n", message = "incorrect companyNip") String companyNip, @NotBlank(message = "name cannot be blank ") String name, @NotBlank(message = "lastName cannot be blank ") String lastName, @NotBlank(message = "email cannot be blank ") @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "incorrect email") String email, @NotBlank(message = "password cannot be blank ") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "incorrect password") String password, boolean status, Date registrationDate, Set<Role> roleSet, Set<Delegation> delegationSet) {
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

    public User(@NotBlank(message = "companyName cannot be blank ") String companyName,
                @NotBlank(message = "companyAddress cannot be blank ") String companyAddress,
                @NotBlank(message = "companyNip cannot be blank ")
                @Pattern(regexp = "^[0-9]{10}$", message = "incorrect companyNip") String companyNip,
                @NotBlank(message = "name cannot be blank ") String name,
                @NotBlank(message = "lastName cannot be blank ") String lastName,
                @NotBlank(message = "email cannot be blank ") @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$",
                        message = "incorrect email") String email,
                @NotBlank(message = "password cannot be blank ")
                @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
                        message = "incorrect password") String password
               ) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyNip = companyNip;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyNip() {
        return companyNip;
    }

    public void setCompanyNip(String companyNip) {
        this.companyNip = companyNip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<Delegation> getDelegationSet() {
        return delegationSet;
    }

    public void setDelegationSet(Set<Delegation> delegationSet) {
        this.delegationSet = delegationSet;
    }

}
