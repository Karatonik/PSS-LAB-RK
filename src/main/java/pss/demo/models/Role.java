package pss.demo.models;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Role {
    @Id
    String roleName;
}
