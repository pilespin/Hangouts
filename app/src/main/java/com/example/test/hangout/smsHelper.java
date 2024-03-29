package com.example.test.hangout;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

/**
 * Created by pilespin on 2/8/17.
 */

public class smsHelper extends BroadcastReceiver {

    public final int SMS_MAX_LENGTH = 160;

    public Boolean sendSms(Context context, String phoneNumber, final String content) {

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.putToast(context, context.getString(R.string.toastNeedPermission));
            return false;
        }

        if (phoneNumber == null || phoneNumber.length() <= 0)
        {
            Toast.putToast(context, "Text is too long");
            return false;
        }
        if (content == null || content.length() <= 0)
        {
            Toast.putToast(context, "Text is empty");
            return false;
        }
        if (content == null || content.length() > SMS_MAX_LENGTH)
        {
            Toast.putToast(context, "Text is too long");
            return false;
        }

        String SMS_SENT = "SMS_SENT";
        String SMS_DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(SMS_SENT), 0);
        PendingIntent deliveredPendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(SMS_DELIVERED), 0);

        // For when the SMS has been sent
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.putToast(context, context.getString(R.string.toastSmsSentSuccesfull));
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.putToast(context, context.getString(R.string.toastGenericFailureCause));
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.putToast(context, context.getString(R.string.toastServiceUnavailable));
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.putToast(context, context.getString(R.string.toastNoPduProvided));
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.putToast(context, context.getString(R.string.toastRadioIsOff));
                        break;
                }
            }
        }, new IntentFilter(SMS_SENT));

        // For when the SMS has been delivered
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.putToast(context, context.getString(R.string.toastSmsDelivered));
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.putToast(context, context.getString(R.string.toastSmsNotDelivered));
                        break;
                }
            }
        }, new IntentFilter(SMS_DELIVERED));

        // Get the default instance of SmsManager
        SmsManager smsManager = SmsManager.getDefault();
        // Send a text based SMS
        smsManager.sendTextMessage(phoneNumber, null, content, sentPendingIntent, deliveredPendingIntent);

        return true;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        //---get the SMS message passed in---
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String content = "";
        String fromPhone = "";
        String toast = "";
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                fromPhone = msgs[i].getOriginatingAddress();
                toast = context.getString(R.string.toastNewSmsFrom) + fromPhone;
                content += msgs[i].getMessageBody().toString();
            }
            abortBroadcast();
            //---display the new SMS message---
            Toast.putToast(context, toast);
            dbHelper db = new dbHelper(context);
            if (db.insertSms("IN", fromPhone, content) == false) {
//                Log.d("------ SMS ------ : ", "Sms not save in database");
            }
            else {
//                Log.d("------ SMS ------ : ", "Sms save in database");
                if (db.checkContactByPhone(context, fromPhone) == false) {
//                    Log.d("------ SMS ------ : ", "Contact not exist");
                    db.insertContact(new Contact(fromPhone, "", fromPhone, "", ""));
                }
            }
        }
    }
}
