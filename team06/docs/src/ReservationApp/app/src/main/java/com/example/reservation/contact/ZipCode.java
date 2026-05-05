package com.example.reservation.contact;
import java.util.Objects;
public class ZipCode {
    private String zipcode;

    public ZipCode(){}

    public ZipCode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCode() {
        return zipcode;
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

        if (!(other instanceof ZipCode)) {
            return false;
        }

        ZipCode theZipCode = (ZipCode) other;
        return Objects.equals(zipcode, theZipCode.zipcode);
    }

    @Override
    public int hashCode() {
        return zipcode == null ? 0 : zipcode.hashCode();
    }

    @Override
    public String toString() {
        return zipcode;
    }
}
