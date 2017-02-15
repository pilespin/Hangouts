package com.example.test.hangout;

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

        if (firstname != null && firstname.length() > 0)
            this.firstname  = firstname;
        if (lastname != null && lastname.length() > 0)
            this.lastname   = lastname;
        if (phone != null && phone.length() > 0)
            this.phone      = phone;
        if (email != null && email.length() > 0)
            this.email      = email;
        if (city != null && city.length() > 0)
            this.city       = city;
    }

    public String getFirstname()    {
        return (lib.getString(this.firstname));
    }

    public String getLastname()     {
        return (lib.getString(this.lastname));
    }

    public String getPhone()        {
        return (lib.getString(this.phone));
    }

    public String getEmail()        {
        return (lib.getString(this.email));
    }

    public String getCity()         {
        return (lib.getString(this.city));
    }
}