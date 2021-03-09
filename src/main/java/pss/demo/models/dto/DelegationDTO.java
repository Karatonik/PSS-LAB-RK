package pss.demo.models.dto;

import pss.demo.enums.AutoCapacity;
import pss.demo.enums.TransportType;
import pss.demo.models.Delegation;
import pss.demo.models.User;


import java.util.Date;

public class DelegationDTO {
    Integer delegationId;

    String description;

    Date dateTimeStart;

    Date dateTimeStop;

    Float travelDietAmount;

    Integer breakfastNumber;

    Integer dinnerNumber;

    Integer supperNumber;

    TransportType transportType;

    Float ticketPrice;

    AutoCapacity autoCapacity;

    Integer km;

    Float accomodationPrice;

    Float otherTicketsPrice;

    Float otherOutlayDesc;

    Float otherOutlayPrice;

    User user;

    public DelegationDTO(Integer delegationId, String description, Date dateTimeStart, Date dateTimeStop, Float travelDietAmount, Integer breakfastNumber, Integer dinnerNumber, Integer supperNumber, TransportType transportType, Float ticketPrice, AutoCapacity autoCapacity, Integer km, Float accomodationPrice, Float otherTicketsPrice, Float otherOutlayDesc, Float otherOutlayPrice, User user) {
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
    public DelegationDTO(Delegation delegation){
        this.delegationId=delegation.getDelegationId();
        this.description=delegation.getDescription();
        this.dateTimeStart=delegation.getDateTimeStart();
        this.dateTimeStop=delegation.getDateTimeStop();
        this.travelDietAmount=delegation.getTravelDietAmount();
        this.breakfastNumber=delegation.getBreakfastNumber();
        this.dinnerNumber=delegation.getDinnerNumber();
        this.supperNumber=delegation.getSupperNumber();
        this.transportType=delegation.getTransportType();
        this.ticketPrice=delegation.getTicketPrice();
        this.autoCapacity=delegation.getAutoCapacity();
        this.km=delegation.getKm();
        this.accomodationPrice=delegation.getAccomodationPrice();
        this.otherTicketsPrice=delegation.getOtherTicketsPrice();
        this.otherOutlayDesc=delegation.getOtherOutlayDesc();
        this.otherOutlayPrice=delegation.getOtherOutlayPrice();
        this.user=delegation.getUser();
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
