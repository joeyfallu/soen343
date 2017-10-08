package com.example.myapp.userCatalog;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private boolean isAdmin;

    public User(){
        this.firstName = null;
        this.lastName = null;
        this.address = null;
        this.email = null;
        this.phoneNumber = null;
        this.id = 0;
        this.isAdmin = false;
    }

    public User(String fn, String ln, String a, String e, String PN, int id, boolean ad){
        this.firstName = fn;
        this.lastName = ln;
        this.address = a;
        this.email = e;
        this.phoneNumber = PN;
        this.id = id;
        this.isAdmin = ad;
    }

    public String toString(){
        String x;
        x = "\n firstName = " + getFirstName() + "\n lastName = " + getLastName() + "\n address = " + getAddress() + "\n phoneNumber = " +getPhoneNumber() + "\n id = " + getId() + "\n isAdmin = " + getIsAdmin() ;
        return x;
    }

    public boolean equals(User e){
        return (this.getFirstName().equals(e.getFirstName()) && this.getLastName().equals(e.getLastName()) && this.getId() == e.getId() && this.getAddress().equals(e.getAddress()) && this.getPhoneNumber().equals(e.getPhoneNumber()) && this.getIsAdmin() == e.getIsAdmin()  );
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public int getId(){
        return id;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }

    public void setFirstName(String fn){
        this.firstName = fn;
    }

    public void setLastName(String ln){
        this.lastName = ln;
    }

    public void setAddress(String ad){
        this.address = ad;
    }

    public void setPhoneNumber(String pn){
        this.phoneNumber = pn;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }


}
