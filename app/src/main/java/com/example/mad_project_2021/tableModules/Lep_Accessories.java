package com.example.mad_project_2021.tableModules;

public class Lep_Accessories {
    String Acc_Lname;
    String Acc_Lprice;
    String Acc_LcomName;
    String Acc_Ldescription;
    String Acc_Limg;

    public Lep_Accessories(String acc_Lname, String acc_Lprice, String acc_LcomName, String acc_Ldescription, String acc_Limg) {
        Acc_Lname = acc_Lname;
        Acc_Lprice = acc_Lprice;
        Acc_LcomName = acc_LcomName;
        Acc_Ldescription = acc_Ldescription;
        Acc_Limg = acc_Limg;
    }

    public String getAcc_Lname() {
        return Acc_Lname;
    }

    public String getAcc_Lprice() {
        return Acc_Lprice;
    }

    public String getAcc_LcomName() {
        return Acc_LcomName;
    }

    public String getAcc_Ldescription() {
        return Acc_Ldescription;
    }

    public String getAcc_Limg() {
        return Acc_Limg;
    }
}
