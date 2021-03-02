package pss.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    @Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message ="incorrect password" )
    //Password must contain at least one digit [0-9].
    // Password must contain at least one lowercase Latin character [a-z].
    //Password must contain at least one uppercase Latin character [A-Z].
    //Password must contain at least one special character like ! @ # & ( ).
    //Password must contain a length of at least 8 characters and a maximum of 20 characters.
    String password;


    @Column(columnDefinition = "boolean default true")
    boolean status;

    @CreationTimestamp
    @Column(updatable = false)
    Date registrationDate;


    @ManyToMany(mappedBy = "userSet")
    Set<Role> roleSet;

    @OneToMany(mappedBy = "user")
    Set<Delegation> delegationSet;




    public User(@NotBlank(message = "companyName cannot be blank ") String companyName,
                @NotBlank(message = "companyAddress cannot be blank ") String companyAddress,
                @NotBlank(message = "companyNip cannot be blank ")
                @Pattern(regexp = "^[0-9]{10}$\n", message = "incorrect companyNip") String companyNip,
                @NotBlank(message = "name cannot be blank ") String name,
                @NotBlank(message = "lastName cannot be blank ") String lastName,
                @NotBlank(message = "email cannot be blank ") @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
                        message = "incorrect email") String email,
                @NotBlank(message = "password cannot be blank ")
                @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
                        message = "incorrect password") String password,
                Set<Role> roleSet) {
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyNip = companyNip;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roleSet=roleSet;
    }
}
