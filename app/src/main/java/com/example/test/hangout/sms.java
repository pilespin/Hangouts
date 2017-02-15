package com.example.test.hangout;

/**
 * Created by pilespin on 2/13/17.
 */

public class sms {

    private String direction    = "";
    private String phone        = "";
    private String content      = "";
    private String time         = "";

    public sms(String direction, String phone, String content, String time) {

        if (direction != null && direction.length() > 0)
            this.direction = direction;
        if (phone != null && phone.length() > 0)
            this.phone = phone;
        if (content != null && content.length() > 0)
            this.content = content;
        if (time != null && time.length() > 0)
            this.time = time;
    }

    public String getContent()    {
        return (lib.getString(this.content));
    }

    public String getDirection()    {
        return (lib.getString(this.direction));
    }

    public String getPhone()    {
        return (lib.getString(this.phone));
    }

    public String getTime()    {
        return (lib.getString(this.time));
    }

}
