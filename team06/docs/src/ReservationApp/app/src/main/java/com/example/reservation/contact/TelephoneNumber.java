package com.example.reservation.contact;

public class TelephoneNumber {
        private String phonenumber;

        public TelephoneNumber(){}

        public TelephoneNumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getTelephoneNumber() {
            return phonenumber;
        }

        public boolean isValid() {
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

            if (!(other instanceof TelephoneNumber)) {
                return false;
            }

            TelephoneNumber thePhoneNumber = (TelephoneNumber) other;
            if(phonenumber == null){
                return thePhoneNumber.getTelephoneNumber() == null;
            }else{
                return  phonenumber.equals(thePhoneNumber.getTelephoneNumber());
            }
        }

        @Override
        public int hashCode() {
             if(phonenumber == null){
                 return 0;
             } else {
                 return phonenumber.hashCode();
             }

        }
        @Override
        public String toString() {
            return phonenumber;
        }
    }


