package me.prokawsar.brodcastreciverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){

            Bundle bundle = intent.getExtras();
            SmsMessage[] body = null;
            String number = "";
            String message = "";

            if (bundle != null ){
                Object[] pdus = (Object[]) bundle.get("pdus");
                String finalMessage = "";
                body = new SmsMessage[pdus.length];

                for (int i = 0 ; i<body.length; i++ ){
                    if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                        String format = bundle.getString("format");
                        body[i] = SmsMessage.createFromPdu((byte[]) pdus[i],format);
                    }else {
                        body[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    }
                    number = body[i].getDisplayOriginatingAddress();
                    message += body[i].getMessageBody();
                    finalMessage = "SMS Receive From : "+number+"\n"+"Message Body : "+message;
                    Toast.makeText(context, ""+finalMessage, Toast.LENGTH_LONG).show();

                    }

                    


            }
        }

    }
}
