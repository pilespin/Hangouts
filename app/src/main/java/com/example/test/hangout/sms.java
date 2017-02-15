package com.example.test.hangout;

/**
 * Created by pilespin on 2/13/17.
 */

public class sms {

    private String fromPhone    = "";
    private String toPhone      = "";
    private String content      = "";
    private String time         = "";

    public sms(String fromPhone, String toPhone, String content, String time) {

        if (fromPhone != null && fromPhone.length() > 0)
            this.fromPhone = fromPhone;
        if (toPhone != null && toPhone.length() > 0)
            this.toPhone = toPhone;
        if (content != null && content.length() > 0)
            this.content = content;
        if (time != null && time.length() > 0)
            this.time = time;
    }

    public String getContent()    {
        if (this.content != null)
            return (this.content);
        else
            return ("");
    }

    public String getFromPhone()    {
        if (this.fromPhone != null)
            return (this.fromPhone);
        else
            return ("");
    }

    public String getToPhone()    {
        if (this.toPhone != null)
            return (this.toPhone);
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
