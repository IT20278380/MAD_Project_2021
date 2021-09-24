package com.example.mad_project_2021.tableModules;

public class Order {
    String Oaddres;
    String Onumber;
    String Oprovince;
    String Oiname;
    String Oiprice;

    public Order(String oaddres, String onumber, String oprovince, String oiname, String oiprice) {
        Oaddres = oaddres;
        Onumber = onumber;
        Oprovince = oprovince;
        Oiname = oiname;
        Oiprice = oiprice;
    }

    public String getOaddres() {
        return Oaddres;
    }

    public String getOnumber() {
        return Onumber;
    }

    public String getOprovince() {
        return Oprovince;
    }

    public String getOiname() {
        return Oiname;
    }

    public String getOiprice() {
        return Oiprice;
    }
}
