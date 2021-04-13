package pss.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import pss.demo.enums.AutoCapacity;
import pss.demo.enums.TransportType;

import javax.persistence.*;
import java.util.Date;



@Entity
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer delegationId;

    String description;

    @CreationTimestamp
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
    @JsonIgnore
    @ManyToOne
    User user;

    public Delegation() {
    }

    public Delegation(Integer delegationId, String description, Date dateTimeStart, Date dateTimeStop, Float travelDietAmount, Integer breakfastNumber, Integer dinnerNumber, Integer supperNumber, TransportType transportType, Float ticketPrice, AutoCapacity autoCapacity, Integer km, Float accomodationPrice, Float otherTicketsPrice, Float otherOutlayDesc, Float otherOutlayPrice, User user) {
        this.delegationId = delegationId;
        this.description = description;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeStop = dateTimeStop;
        this.travelDietAmount = travelDietAmount;
        this.breakfastNumber = breakfastNumber;
        this.dinnerNumber = dinnerNumber;
        this.supperNumber = supperNumber;
        this.transportType = transportType;
        this.ticketPrice = ticketPrice;
        this.autoCapacity = autoCapacity;
        this.km = km;
        this.accomodationPrice = accomodationPrice;
        this.otherTicketsPrice = otherTicketsPrice;
        this.otherOutlayDesc = otherOutlayDesc;
        this.otherOutlayPrice = otherOutlayPrice;
        this.user = user;
    }

    public Integer getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(Integer delegationId) {
        this.delegationId = delegationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(Date dateTimeStart) {
        this.dateTimeStart = dateTimeStart;
    }

    public Date getDateTimeStop() {
        return dateTimeStop;
    }

    public void setDateTimeStop(Date dateTimeStop) {
        this.dateTimeStop = dateTimeStop;
    }

    public Float getTravelDietAmount() {
        return travelDietAmount;
    }

    public void setTravelDietAmount(Float travelDietAmount) {
        this.travelDietAmount = travelDietAmount;
    }

    public Integer getBreakfastNumber() {
        return breakfastNumber;
    }

    public void setBreakfastNumber(Integer breakfastNumber) {
        this.breakfastNumber = breakfastNumber;
    }

    public Integer getDinnerNumber() {
        return dinnerNumber;
    }

    public void setDinnerNumber(Integer dinnerNumber) {
        this.dinnerNumber = dinnerNumber;
    }

    public Integer getSupperNumber() {
        return supperNumber;
    }

    public void setSupperNumber(Integer supperNumber) {
        this.supperNumber = supperNumber;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public AutoCapacity getAutoCapacity() {
        return autoCapacity;
    }

    public void setAutoCapacity(AutoCapacity autoCapacity) {
        this.autoCapacity = autoCapacity;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }

    public Float getAccomodationPrice() {
        return accomodationPrice;
    }

    public void setAccomodationPrice(Float accomodationPrice) {
        this.accomodationPrice = accomodationPrice;
    }

    public Float getOtherTicketsPrice() {
        return otherTicketsPrice;
    }

    public void setOtherTicketsPrice(Float otherTicketsPrice) {
        this.otherTicketsPrice = otherTicketsPrice;
    }

    public Float getOtherOutlayDesc() {
        return otherOutlayDesc;
    }

    public void setOtherOutlayDesc(Float otherOutlayDesc) {
        this.otherOutlayDesc = otherOutlayDesc;
    }

    public Float getOtherOutlayPrice() {
        return otherOutlayPrice;
    }

    public void setOtherOutlayPrice(Float otherOutlayPrice) {
        this.otherOutlayPrice = otherOutlayPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
