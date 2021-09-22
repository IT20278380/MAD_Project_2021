package com.example.mad_project_2021.tableModules;

public class Admin {
    String AuserName;
    String Aemail;
    String AmobileNumber;
    String Apassword;

    public Admin(String auserName, String aemail, String amobileNumber, String apassword) {
        AuserName = auserName;
        Aemail = aemail;
        AmobileNumber = amobileNumber;
        Apassword = apassword;
    }

    public String getAuserName() { return AuserName; }

    public String getAemail() {
        return Aemail;
    }

    public String getAmobileNumber() {
        return AmobileNumber;
    }

    public String getApassword() {
        return Apassword;
    }
}
