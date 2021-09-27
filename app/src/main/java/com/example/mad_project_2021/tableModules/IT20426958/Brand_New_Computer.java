package com.example.mad_project_2021.tableModules.IT20426958;

public class Brand_New_Computer {
    String New_Cname;
    String New_Cprice;
    String New_Cgenarate;
    String New_Cdescription;
    String New_Cimage;

    public Brand_New_Computer(String new_Cname, String new_Cprice, String new_Cgenarate, String new_Cdescription, String new_cimage) {
        New_Cname = new_Cname;
        New_Cprice = new_Cprice;
        New_Cgenarate = new_Cgenarate;
        New_Cdescription = new_Cdescription;
        New_Cimage = new_cimage;
    }

    public String getNew_Cname() {
        return New_Cname;
    }

    public String getNew_Cprice() {
        return New_Cprice;
    }

    public String getNew_Cgenarate() {
        return New_Cgenarate;
    }

    public String getNew_Cdescription() {
        return New_Cdescription;
    }

    public String getNew_Cimage() {
        return New_Cimage;
    }
}
