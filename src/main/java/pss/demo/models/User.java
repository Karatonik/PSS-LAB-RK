package pss.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.hash.Hashing;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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


    @Column(columnDefinition = "boolean default false")
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

    @JsonIgnore
    private String userKey;

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
        this.userKey =Hashing.sha256()
                .hashString(String.valueOf(hashCode()), StandardCharsets.UTF_8)
                .toString();
        this.status=false;
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
        this.userKey= Hashing.sha256()
                .hashString(String.valueOf(hashCode()), StandardCharsets.UTF_8)
                .toString();
        this.status=false;

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


    public String getUserKey() {
        return userKey;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isStatus() == user.isStatus() &&
                Objects.equals(getUserId(), user.getUserId()) &&
                Objects.equals(getCompanyName(), user.getCompanyName()) &&
                Objects.equals(getCompanyAddress(), user.getCompanyAddress()) &&
                Objects.equals(getCompanyNip(), user.getCompanyNip()) &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getRegistrationDate(), user.getRegistrationDate()) &&
                Objects.equals(getRoleSet(), user.getRoleSet()) &&
                Objects.equals(getDelegationSet(), user.getDelegationSet()) &&
                Objects.equals(userKey, user.userKey);
    }



    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getCompanyName(), getCompanyAddress(), getCompanyNip(), getName(), getLastName(), getEmail(), getPassword(), isStatus(), getRegistrationDate(), getRoleSet(), getDelegationSet(), userKey);
    }
    public void getNewKey(){
        this.userKey = Hashing.sha256()
                .hashString(String.valueOf(hashCode()), StandardCharsets.UTF_8)
                .toString();}
}
