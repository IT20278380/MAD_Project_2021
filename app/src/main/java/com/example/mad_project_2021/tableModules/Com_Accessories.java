package com.example.mad_project_2021.tableModules;

public class Com_Accessories {
    String Acc_Cname;
    String Acc_Cprice;
    String Acc_CcomName;
    String Acc_Cdescription;
    String Acc_Cimg;

    public Com_Accessories(String acc_Cname, String acc_Cprice, String acc_CcomName, String acc_Cdescription, String acc_Cimg) {
        Acc_Cname = acc_Cname;
        Acc_Cprice = acc_Cprice;
        Acc_CcomName = acc_CcomName;
        Acc_Cdescription = acc_Cdescription;
        Acc_Cimg = acc_Cimg;
    }

    public String getAcc_Cname() {
        return Acc_Cname;
    }

    public String getAcc_Cprice() {
        return Acc_Cprice;
    }

    public String getAcc_CcomName() {
        return Acc_CcomName;
    }

    public String getAcc_Cdescription() {
        return Acc_Cdescription;
    }

    public String getAcc_Cimg() {
        return Acc_Cimg;
    }
}
