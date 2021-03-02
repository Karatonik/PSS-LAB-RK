package pss.demo.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pss.demo.enums.AutoCapacity;
import pss.demo.enums.TransportType;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer delegationId;

    String description;

    @Column(nullable =false)
    Date dateTimeStart;

    @Column(nullable = false)
    Date dateTimeStop;

    @Column(columnDefinition = "float default 30")
    Float travelDietAmount;

    @Column(columnDefinition = "integer default 0")
    Integer breakfastNumber;

    @Column(columnDefinition = "integer default 0")
    Integer dinnerNumber;

    @Column(columnDefinition = "integer default 0")
    Integer supperNumber;

    TransportType transportType;

    Float ticketPrice;

   AutoCapacity autoCapacity;

    Integer km;

    Float accomodationPrice;

    Float otherTicketsPrice;

    Float otherOutlayDesc;

    Float otherOutlayPrice;

    @ManyToOne
    User user;
}
