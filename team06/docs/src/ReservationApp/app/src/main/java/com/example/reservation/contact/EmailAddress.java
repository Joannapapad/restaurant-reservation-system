package com.example.reservation.contact;

public class EmailAddress {
    private String email;

    public EmailAddress(){}
    public EmailAddress(String email){
        this.email = email;
    }
    public String getAddress(){
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid(){
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof EmailAddress)) {
            return false;
        }
        EmailAddress theEmail = (EmailAddress) other;
        if(email == null){
            return theEmail.getAddress() == null;
        }else{
            return email.equals(theEmail.getAddress());
        }
    }

    @Override
    public int hashCode() {
        if(email == null){
            return 0;
        }else{
            return email.hashCode();
        }
    }

    @Override
    public String toString() {
        return email;
    }
}
