package com.ecommerce.adminapp.model;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String stAddress;
    private String city;
    private String state;
    private String zipcode;

    public Customer(){}

    public Customer(int customerId, String firstName, String lastName, String stAddress, String city, String state,
                    String zipcode) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stAddress = stAddress;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullname(String firstName, String lastName){
        return firstName + " " + lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStAddress() {
        return stAddress;
    }

    public void setStAddress(String stAddress) {
        this.stAddress = stAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String createShippingAddress(String stAddress, String city,
                                        String state, String zipcode){
        return stAddress + ", " + city + ", " + state + ", " + zipcode;
    }
}
