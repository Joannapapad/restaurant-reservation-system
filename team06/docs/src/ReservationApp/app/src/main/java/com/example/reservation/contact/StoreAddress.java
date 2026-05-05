package com.example.reservation.contact;

import java.util.Objects;

public class StoreAddress {
    private String street;
    private String number;
    private ZipCode zip;
    private String city;
    private String country;

    public StoreAddress(){}
    public StoreAddress(String street, String number,ZipCode zip, String city , String country){
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
        this.country = country;
    }

    public void setStreet(String street){
        this.street= street;
    }
    public void setNumber(String number){
        this.number = number;
    }
    public void setZip(ZipCode zip){
        this.zip = zip;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public String getStreet(){
        return this.street;
    }
    public String getNumber(){
        return this.number;
    }
    public ZipCode getZip(){
        return this.zip;
    }
    public String getCity(){
        return this.city;
    }
    public String getCountry(){
        return this.country;
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof StoreAddress)) {
            return false;
        }

        StoreAddress address = (StoreAddress) other;
        if(!Objects.equals(street,address.getStreet())){
            return false;
        }
        if(!Objects.equals(number,address.getNumber())){
            return false;
        }
        if(!Objects.equals(zip,address.getZip())){
            return false;
        }
        if(!Objects.equals(city,address.getCity())){
            return false;
        }
        if(!Objects.equals(country,address.getCountry())){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (street == null && number == null && city == null
                && zip == null && country == null) {
            return 0;
        }
        int result = 0 ;
        if(street!= null){
            result = result*13 + street.hashCode();
        }
        if(number!= null){
            result = result*13 + number.hashCode();
        }
        if(city!= null){
            result = result*13 + city.hashCode();
        }
        if(zip!= null){
            result = result*13 + zip.hashCode();
        }
        if(country!= null){
            result = result*13 + country.hashCode();
        }
        return result;
    }
    @Override
    public String toString() {
        return street + " " + number + ", " + zip.getCode() + " " + city + ", " + country;
    }

}
