package com.habib.eshop.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ShippingAddressDTO {
    @NotEmpty
    private String address;
    private String address2;

    @NotEmpty
    private String state;

    @NotEmpty
    private String zip;

    @NotEmpty
    private String country;

    @NotEmpty
    @Pattern(regexp = "^(?:\\+88|01)?\\d{11}",
            message = "Must be a valid Bangladeshi phone number"
    )
    private String mobileNumber;

    public String getAddress() {
        return address;
    }

    public String getAddress2() {
        return address2;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
