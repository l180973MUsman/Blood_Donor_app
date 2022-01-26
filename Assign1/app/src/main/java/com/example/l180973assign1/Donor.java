package com.example.l180973assign1;

public class Donor {

    String name;
    String B_group;
    String contact;
    String loc;
    String status;

    public Donor() {
        name = null;
        B_group = null;
        contact = null;
        loc = null;
        status = null;
    }

    public Donor(String name, String group, String con, String loc, String status) {
        this.name = name;
        this.B_group = group;
        this.contact = con;
        this.loc = loc;
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getB_group() {
        return B_group;
    }

    public void setB_group(String b_group) {
        B_group = b_group;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
