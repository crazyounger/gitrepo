package com.certusnet.common.receiver;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * @author admin
 * 
 */
public class SmsChangeReceiver extends BaseReceiver {
	public static SmsMessage[] getSmsMessage(Intent intent){
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			Object[] pdus = (Object[]) bundle.get("pdus");
			int size = pdus.length;
			SmsMessage[] mges = new SmsMessage[size];
			for (int i = 0; i < size; i++) {
				mges[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			}
			 return mges;
		}
		return null;
	}
}
