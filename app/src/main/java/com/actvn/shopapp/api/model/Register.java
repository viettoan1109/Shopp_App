package com.actvn.shopapp.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {
    @SerializedName("reg_first_name")
    @Expose
    private String regFirstname;
    @SerializedName("reg_email")
    @Expose
    private String regEmail;
    @SerializedName("reg_password")
    @Expose
    private String regPassword;
    @SerializedName("reg_address1")
    @Expose
    private  String regAddress1;
    @SerializedName("reg_phone")
    @Expose
    private  String regPhone;

    public Register(String regFirstname, String regEmail, String regPassword, String regAddress1, String regPhone) {
        this.regFirstname = regFirstname;
        this.regEmail = regEmail;
        this.regPassword = regPassword;
        this.regAddress1 = regAddress1;
        this.regPhone = regPhone;
    }

    public String getRegFirstname() {
        return regFirstname;
    }

    public void setRegFirstname(String regFirstname) {
        this.regFirstname = regFirstname;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegPassword() {
        return regPassword;
    }

    public void setRegPassword(String regPassword) {
        this.regPassword = regPassword;
    }

    public String getRegAddress1() {
        return regAddress1;
    }

    public void setRegAddress1(String regAddress1) {
        this.regAddress1 = regAddress1;
    }

    public String getRegPhone() {
        return regPhone;
    }

    public void setRegPhone(String regPhone) {
        this.regPhone = regPhone;
    }
}
