package com.example.mad_project_2021.tableModules.IT20276232;

public class Delivers {

    String DuserName;
    String Demail;
    String DmobileNumber;
    String Dpassword;
    String Dprovince;

    public Delivers(String duserName, String demail, String dmobileNumber, String dpassword, String dprovince) {
        DuserName = duserName;
        Demail = demail;
        DmobileNumber = dmobileNumber;
        Dpassword = dpassword;
        Dprovince = dprovince;
    }

    public Delivers(String duserName, String demail, String dmobileNumber) {
    }

    public String getDuserName() {
        return DuserName;
    }

    public String getDemail() {
        return Demail;
    }

    public String getDmobileNumber() {
        return DmobileNumber;
    }

    public String getDpassword() {
        return Dpassword;
    }

    public String getDprovince() {
        return Dprovince;
    }
}
