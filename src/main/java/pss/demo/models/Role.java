package pss.demo.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role {
    @Id
    String roleName;

@ManyToMany
Set<User> userSet;
}
