package com.efjava.hard;

import java.util.concurrent.atomic.AtomicLong;

public class employee {

    private static final AtomicLong counter = new AtomicLong();

    public employee( String fname, String lname, double salary, boolean has401k, int companyID, int healthPlanId) {
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
        this.has401k = has401k;
        this.companyID = companyID;
        this.healthPlanId = healthPlanId;
    }

    private long id;

    public static AtomicLong getCounter() {
        return counter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isHas401k() {
        return has401k;
    }

    public void setHas401k(boolean has401k) {
        this.has401k = has401k;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public int getHealthPlanId() {
        return healthPlanId;
    }

    public void setHealthPlanId(int healthPlanId) {
        this.healthPlanId = healthPlanId;
    }

    private String fname;
    private String lname;
    private double salary;
    private boolean has401k;
    private int companyID;
    private int healthPlanId;


}
