package com.example.test.hangout;

/**
 * Created by pilespin on 2/13/17.
 */

public class sms {

    private String phone    = "";
    private String content  = "";
    private String time     = "";

    public sms(String phone, String content, String time) {

        phone   = "";
        content = "";
        time    = "";

        if (phone != null && phone.length() > 0)
            this.phone = phone;
        if (content != null && content.length() > 0)
            this.content = content;
        if (time != null && time.length() > 0)
            this.content = time;
    }

    public String getContent()    {
//        return ("Hello123");
        if (this.content != null)
            return (this.content);
        else
            return ("");
    }

    public String getPhone()    {
        if (this.phone != null)
            return (this.phone);
        else
            return ("");
    }

    public String getTime()    {
        if (this.time != null)
            return (this.time);
        else
            return ("");
    }

}
