package com.certusnet.common.net;

public interface RequestListener {
	public abstract void onComplete(String reponse);

	public abstract void onError(int code, String message);
	public abstract void onError(Exception e);
}
