package com.group18.entity.dto.response;

import java.util.Objects;

public class BuildingResponse {
    private Long id;
    private String description;
    private String typeOfApartment;
    private int noOfGuest;
    private int noOfBedrooms;
    private int noOfBathrooms;
    private int noOfBeds;
    private boolean parking;
    private boolean kitchen;
    private boolean wifi;
    private String address;
    private boolean washer;
    private boolean safetyEquipment;

    public BuildingResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfApartment() {
        return typeOfApartment;
    }

    public void setTypeOfApartment(String typeOfApartment) {
        this.typeOfApartment = typeOfApartment;
    }

    public int getNoOfGuest() {
        return noOfGuest;
    }

    public void setNoOfGuest(int noOfGuest) {
        this.noOfGuest = noOfGuest;
    }

    public int getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(int noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public int getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public void setNoOfBathrooms(int noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getWasher() {
        return washer;
    }

    public void setWasher(boolean washer) {
        this.washer = washer;
    }

    public boolean isSafetyEquipment() {
        return safetyEquipment;
    }

    public void setSafetyEquipment(boolean safetyEquipment) {
        this.safetyEquipment = safetyEquipment;
    }

    public int getNoOfBeds() {return noOfBeds;}

    public void setNoOfBeds(int noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingResponse that = (BuildingResponse) o;
        return noOfGuest == that.noOfGuest && noOfBedrooms == that.noOfBedrooms && noOfBathrooms == that.noOfBathrooms && noOfBeds == that.noOfBeds && parking == that.parking && kitchen == that.kitchen && wifi == that.wifi && washer == that.washer && safetyEquipment == that.safetyEquipment && Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(typeOfApartment, that.typeOfApartment) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, typeOfApartment, noOfGuest, noOfBedrooms, noOfBathrooms, noOfBeds, parking, kitchen, wifi, address, washer, safetyEquipment);
    }
}
