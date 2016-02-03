package com.certusnet.image;

import java.io.InputStream;

import com.airshiplay.mobile.util.ScreenUtil;
import com.certusnet.common.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

public class LocalImage implements IImageCapturer {
	protected static ImageCache webImageCache;
	private String filePath;
	private int width;
	private int height;
	private int defaultImage;
	private int sampleSize = 1;

	public LocalImage(String filepath) {
		this.filePath = filepath;
	}

	/**
	 * @param filepath
	 * @param width
	 *            unit dp
	 * @param height
	 *            unit dp
	 */
	public LocalImage(String filepath, int width, int height) {
		this.filePath = filepath;
		this.width = width;
		this.height = height;
	}

	@Override
	public Bitmap get(Context context) {
		if (webImageCache == null)
			return null;
		return webImageCache.getBitmapFromMemory(this.filePath);
	}

	@Override
	public String getCacheKey() {
		return this.filePath;
	}

	@Override
	public void recycle() {
		Bitmap bitmap = webImageCache.getBitmapFromMemory(filePath);
		if (bitmap != null) {
			webImageCache.remove(filePath);
			bitmap.recycle();
		}
	}

	@Override
	public Bitmap request(Context context) {

		if (webImageCache == null)
			webImageCache = ImageCache.getImageCache(context);
		Bitmap bitmap = null;
		if (this.filePath != null) {
			if (this.filePath.startsWith("android.resource:")) {
				String params[] = filePath.split("/+");
				Resources r = context.getResources();
				int resID = r.getIdentifier(params[3], "drawable", params[1]);
				bitmap = BitmapFactory.decodeResource(r, resID);
			} else {
				bitmap = getImageResource(context, filePath);
			}
			if (bitmap != null) {
				webImageCache.cacheBitmapToMemory(filePath, bitmap);
			}
		}
		return bitmap;
	}

	public Bitmap getImageResource(Context context, String filepath) {
		return getSketchPicture(context, filepath, ScreenUtil.dp2px(context, width), ScreenUtil.dp2px(context, height));
	}

	private Bitmap getSketchPicture(Context context, String filepath, int width, int height) {
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(filepath, options);
			int hSample = (int) Math.ceil(options.outHeight / height);
			int wSample = (int) Math.ceil(options.outWidth / width);
			if ((hSample > 1) || (wSample > 1)) {
				if (hSample > wSample)
					options.inSampleSize = hSample;
				else
					options.inSampleSize = wSample;
			} else if (sampleSize != 0) {
				options.inSampleSize = sampleSize;
			}
			options.inJustDecodeBounds = false;
			return BitmapLoader.getBitmapFromDisk(filepath, options);

		} catch (Exception localException) {
			return BitmapFactory.decodeResource(context.getResources(), defaultImage);
		}
	}

	public LocalImage setDefaultImage(int defaultResId) {
		this.defaultImage = defaultResId;
		return this;
	}

	public LocalImage setSampleSize(int sampleSize) {
		this.sampleSize = sampleSize;
		return this;
	}
}
