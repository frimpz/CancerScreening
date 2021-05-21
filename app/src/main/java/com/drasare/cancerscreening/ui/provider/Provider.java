package com.drasare.cancerscreening.ui.provider;

public class Provider {

    String name;
    String telephone;
    String city;
    String Address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Provider(String name, String telephone, String city, String address) {
        this.name = name;
        this.telephone = telephone;
        this.city = city;
        Address = address;
    }



}
