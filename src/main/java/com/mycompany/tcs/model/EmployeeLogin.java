package com.mycompany.tcs.model;

import java.io.Serializable;

public class EmployeeLogin implements Serializable 
{
    private String lpassword;
    private int lverifykey, leid;

    public String getLpassword() {
        return lpassword;
    }

    public void setLpassword(String lpassword) {
        this.lpassword = lpassword;
    }

    public int getLverifykey() {
        return lverifykey;
    }

    public void setLverifykey(int lverifykey) {
        this.lverifykey = lverifykey;
    }

    public int getLeid() {
        return leid;
    }

    public void setLeid(int leid) {
        this.leid = leid;
    }

   
}