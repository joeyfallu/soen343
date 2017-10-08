package com.example.myapp.userCatalog;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;
    private int isAdmin;

    public User(){
        this.firstName = null;
        this.lastName = null;
        this.address = null;
        this.email = null;
        this.phoneNumber = null;
        this.id = 0;
        this.isAdmin = 0;
    }

    public User(int id,String fn, String ln, String a, String PN, String e, String pw, int ad){
        this.firstName = fn;
        this.lastName = ln;
        this.address = a;
        this.email = e;
        this.phoneNumber = PN;
        this.id = id;
        this.isAdmin = ad;
        this.password= pw;
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

    public int getId(){ return id; }

    public String getPassword(){return password;}

    public int getIsAdmin(){
        return isAdmin;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {this.email = email; }

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

    public void setPassword(String password){this.password = password;}

    public void setIsAdmin(int isAdmin){
        this.isAdmin = isAdmin;
    }


}
