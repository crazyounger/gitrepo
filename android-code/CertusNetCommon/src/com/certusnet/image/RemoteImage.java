package com.certusnet.image;

import com.airshiplay.mobile.util.ScreenUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.WindowManager;

public class RemoteImage implements IImageCapturer {
	protected static ImageCache imageCache;
	protected boolean mAllowStop;
	protected float mCornerPixel = 90.0f;
	protected boolean mCut;
	protected boolean mRotate;
	protected boolean mRoundCorner;
	/** unit px */
	protected float mWidth;
	/** unit px */
	protected float mHeight;
	protected String url;
	protected float degree = 0.0f;

	public void setmRoundCorner(boolean mRoundCorner) {
		this.mRoundCorner = mRoundCorner;
	}

	public void setmHeight(float mHeight) {
		this.mHeight = mHeight;
	}

	public void setmAllowStop(boolean mAllowStop) {
		this.mAllowStop = mAllowStop;
	}

	public void setmCornerPixel(float mCornerPixel) {
		this.mCornerPixel = mCornerPixel;
	}

	public void setmCut(boolean mCut) {
		this.mCut = mCut;
	}

	public void setmRotate(boolean mRotate) {
		this.mRotate = mRotate;
	}

	public void setmWidth(float mWidth) {
		this.mWidth = mWidth;
	}

	public void setDegree(float degree) {
		this.degree = degree;
	}

	public RemoteImage(String url) {
		if (!TextUtils.isEmpty(url))
			url = url.trim();
		this.url = url;
	}

	@Override
	public Bitmap get(Context context) {
		if (imageCache == null || TextUtils.isEmpty(this.url))
			return null;
		return imageCache.getBitmapFromMemory(this.url);
	}

	@Override
	public String getCacheKey() {
		return this.url;
	}

	@Override
	public void recycle() {
		Bitmap localBitmap = imageCache.getBitmapFromMemory(this.url);
		if (localBitmap != null) {
			imageCache.remove(this.url);
			localBitmap.recycle();
		}
	}

	@Override
	public Bitmap request(Context context) {
		if (imageCache == null) {
			imageCache = ImageCache.getImageCache(context);
		}
		if (TextUtils.isEmpty(this.url))
			return null;

		Bitmap bitmap = imageCache.getBitmapFromDisk(this.url, mWidth, mHeight);
		if (bitmap == null) {
			bitmap = BitmapLoader.downloadBitmap(this.url, mWidth, mHeight);
		}
		if (bitmap != null) {
			if (this.mCut) {
				bitmap = cutImageCenter(bitmap, context);
			}
			if (this.mRoundCorner) {
				bitmap = setRoundCorner(bitmap);
			}
			if (this.mRotate && degree != 0) {
				bitmap = getRotateImage(bitmap, degree);
			}
			if (bitmap != null) {
				imageCache.cacheBitmapToMemory(this.url, bitmap);
			}
		}
		if (this.mAllowStop)
			try {
				Thread.sleep(1L);
			} catch (InterruptedException e) {
			}
		return bitmap;
	}

	private Bitmap cutImageCenter(Bitmap bitmap, Context context) {
		int screenWidth =(int) mWidth;
		int screenHeight =(int) mHeight;
		int x = 0;
		int y = 0;
		int width=bitmap.getWidth();
		int height=bitmap.getHeight();
		if (width > screenWidth) {
			x = bitmap.getWidth() - screenWidth;
			width =screenWidth;
		} else if (height > screenHeight) {
			y = bitmap.getHeight() - screenHeight;
			height=screenHeight;
		}
		if (x > 0 || y > 0) {
			Matrix matrix = new Matrix();
			bitmap = Bitmap.createBitmap(bitmap, x / 2, y / 2, width, height, matrix, true);
		}
		return bitmap;
	}

	/**
	 * @param degrees
	 *            >0 则顺时针旋转，<0逆时针旋转
	 */
	private Bitmap getRotateImage(Bitmap bitmap, float degrees) {

		Matrix matrix = new Matrix();
		matrix.setRotate(degrees, bitmap.getWidth() / 2, bitmap.getHeight() / 2); // 围绕中心转
		bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

		return bitmap;

	}

	private Bitmap setRoundCorner(Bitmap bitmap) {
		try {
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(output);
			final Paint paint = new Paint();
			final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			final RectF rectF = new RectF(new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()));
			// final float roundPx = mCornerPixel; // 圆角
			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(Color.BLACK);
			canvas.drawRoundRect(rectF, mCornerPixel, mCornerPixel, paint);
			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); // Mode.SRC_IN
			final Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
			canvas.drawBitmap(bitmap, src, rect, paint);
			return output;
		} catch (Exception e) {
			return bitmap;
		}

	}

	public static RemoteImage getListIcon(String url) {
		RemoteImage remoteImage = new RemoteImage(url);
		remoteImage.mWidth = 1;
		remoteImage.mHeight = 1;
		remoteImage.mRoundCorner = false;
		return remoteImage;
	}

	
	public static RemoteImage getListIcon(String url, float mWidth, float mHeight) {
		RemoteImage remoteImage = new RemoteImage(url);
		remoteImage.mWidth = mWidth;
		remoteImage.mHeight = mHeight;
		remoteImage.mRoundCorner = false;
		remoteImage.mCut=false;
		return remoteImage;
	}
	/**
	 * width fixed,screen width
	 * 
	 * @param url
	 * @return
	 */
	public static RemoteImage getScreenShot(String url) {
		return getScreenShot(url, false);
	}

	public static RemoteImage getScreenShot(String url, boolean halfWidth) {
		RemoteImage remoteImage = new RemoteImage(url);
		if (halfWidth)
			remoteImage.mWidth = ScreenUtil.resolutionXY[0] / 2;
		else {
			remoteImage.mWidth = ScreenUtil.resolutionXY[0];
		}
		remoteImage.mHeight = 1;
		return remoteImage;
	}

	/**
	 * Height 48dp fixed
	 * 
	 * @param url
	 * @return
	 */
	public static RemoteImage getWebIcon(String url) {
		return getWebIcon(url, false, false, false);
	}

	public static RemoteImage getWebIcon(String url, boolean screenHeight) {
		return getWebIcon(url, screenHeight, false, false);
	}

	public static RemoteImage getWebIcon(String url, boolean screenHeight, boolean allowStop, boolean cut) {
		RemoteImage remoteImage = new RemoteImage(url);
		if (screenHeight) {
			remoteImage.mHeight = ScreenUtil.resolutionXY[1];
		} else {
			remoteImage.mHeight = ScreenUtil.dp2px(48);
		}
		remoteImage.mWidth = 1;
		remoteImage.mAllowStop = allowStop;
		remoteImage.mCut = cut;
		return remoteImage;
	}

}
