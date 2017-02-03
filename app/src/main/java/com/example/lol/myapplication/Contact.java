package com.example.lol.myapplication;

/**
 * Created by pilespin on 2/2/17.
 */

public class Contact {

    private String firstname = "";
    private String lastname = "";
    private String phone = "";
    private String email = "";
    private String city = "";

    public Contact(String firstname, String lastname, String phone, String email, String city) {

        this.firstname  = "";
        this.lastname   = "";
        this.phone      = "";
        this.email      = "";
        this.city       = "";

        if (firstname.length() > 0)
            this.firstname  = firstname;
        if (firstname.length() > 0)
            this.lastname   = lastname;
        if (firstname.length() > 0)
            this.phone      = phone;
        if (firstname.length() > 0)
            this.email      = email;
        if (firstname.length() > 0)
            this.city       = city;
    }

    public String getFirstname()    {
        if (this.firstname != null)
            return (this.firstname);
        else
            return ("");
    }
    public String getLastname()     {
        if (this.lastname != null)
            return (this.lastname);
        else
            return ("");
    }
    public String getPhone()        {
        if (this.phone != null)
            return (this.phone);
        else
            return ("");
    }
    public String getEmail()        {
        if (this.email != null)
            return (this.email);
        else
            return ("");
    }
    public String getCity()         {
        if (this.city != null)
            return (this.city);
        else
            return ("");
    }
}
