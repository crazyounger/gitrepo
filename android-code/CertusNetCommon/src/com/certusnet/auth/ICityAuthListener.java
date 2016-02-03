package com.certusnet.auth;

import android.os.Bundle;

public interface ICityAuthListener {
	public void onSuccess(Bundle values);

	public void onFailure(int code, String message);

	public void onCancel();
}
