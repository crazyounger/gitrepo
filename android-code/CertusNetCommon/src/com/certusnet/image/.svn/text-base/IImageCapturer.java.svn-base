package com.certusnet.image;

import android.content.Context;
import android.graphics.Bitmap;

public interface IImageCapturer {
	public abstract Bitmap get(Context context);

	public abstract String getCacheKey();

	public abstract void recycle();

	/**
	 * request load bitmap（local or，remote）
	 * 
	 * @param context
	 * @return
	 */
	public abstract Bitmap request(Context context);
}
