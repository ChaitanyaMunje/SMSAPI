package com.example.smsapi;
// Data for passing it into recycler view of Contact Fragment.
class ContactData {

    private String name;

    public ContactData(String name, String phno, String email) {
        this.name = name;
        this.phno = phno;
        this.email = email;
    }

    private String phno;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
}
