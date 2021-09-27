package com.example.mad_project_2021.tableModules.IT20276232;

public class Customer {
    String CuserName;
    String Cemail;
    String CmobileNumber;
    String Cpassword;
    String Cprovince;

    public Customer(String cuserName, String cemail, String cmobileNumber, String cpassword, String cprovince) {
        CuserName = cuserName;
        Cemail = cemail;
        CmobileNumber = cmobileNumber;
        Cpassword = cpassword;
        Cprovince = cprovince;
    }

    public String getCuserName() {
        return CuserName;
    }

    public String getCemail() {
        return Cemail;
    }

    public String getCmobileNumber() {
        return CmobileNumber;
    }

    public String getCpassword() {
        return Cpassword;
    }

    public String getCprovince() {
        return Cprovince;
    }
}
