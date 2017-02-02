package com.example.lol.myapplication;

/**
 * Created by pilespin on 2/2/17.
 */

public class Contact {

    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String city;

    public Contact(String firstname, String lastname, String phone, String email, String city) {

        this.firstname  = firstname;
        this.lastname   = lastname;
        this.phone      = phone;
        this.email      = email;
        this.city       = city;
    }

    public String getFirstname()    { return (this.firstname);     }
    public String getLastname()     { return (this.lastname);      }
    public String getPhone()        { return (this.phone);         }
    public String getEmail()        { return (this.email);         }
    public String getCity()         { return (this.city);          }
}
