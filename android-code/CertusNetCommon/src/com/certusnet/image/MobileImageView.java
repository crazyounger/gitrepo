package com.certusnet.image;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.airshiplay.mobile.util.TelephoneUtil;

public class MobileImageView {

	public static final int KEY = 110;
	private static final int LOADING_THREADS = 4;
	private static ExecutorService threadPool = Executors
			.newFixedThreadPool(LOADING_THREADS);
	private boolean SaveFlowMode;
	protected CaptureImageTask currentTask;
	private OnSetImageFinished finishedHandler;
	private Drawable mDefaultDrawable;
	private boolean isRemoved;
	boolean mCanAnimation;
	private IImageCapturer mCapturer;
	protected Context mContext;
	private boolean mForce;

	protected WeakReference<ImageView> mImageViewRef;
	private boolean mOnlyLoadCache;

	public MobileImageView(ImageView imageView) {
		imageView.setTag(this);
		mContext = imageView.getContext().getApplicationContext();
		mImageViewRef = new WeakReference<ImageView>(imageView);
	}

	public MobileImageView(ImageView imageView, Integer defaultDrawableResid) {
		mContext = imageView.getContext().getApplicationContext();
		mImageViewRef = new WeakReference<ImageView>(imageView);
		imageView.setTag(this);
		if (defaultDrawableResid != null) {
			mDefaultDrawable = mContext.getResources().getDrawable(
					defaultDrawableResid.intValue());
			imageView.setImageDrawable(mDefaultDrawable);
		}
	}

	public void setImage(String url) {
		RemoteImage remoteImage = RemoteImage.getListIcon(url);
		setImage(remoteImage);
	}

	public void setImage(String url, float mWidth, float mHeight) {
		RemoteImage remoteImage = RemoteImage.getListIcon(url, mWidth, mHeight);
		setImage(remoteImage);
	}
	
	public void setImage(String url, boolean onlyLoadCache) {
		RemoteImage remoteImage = RemoteImage.getListIcon(url);
		setImage(remoteImage, onlyLoadCache);
	}

	public void setImage(IImageCapturer iImageCapturer) {
		setImage(iImageCapturer, false);
	}

	public void setImage(IImageCapturer iImageCapturer,
			OnSetImageFinished onSetImageFinished) {
		finishedHandler = onSetImageFinished;
		setImage(iImageCapturer, false);
	}

	public void setImage(IImageCapturer iImageCapturer, boolean onlyLoadCache) {
		setImage(iImageCapturer, onlyLoadCache, false);
	}

	public void setImage(IImageCapturer iImageCapturer, boolean onlyLoadCache,
			boolean animate) {
		if (iImageCapturer == null)
			return;
		if (!canLoad(mContext)) {
			setDefautlImage();
		} else {
			isRemoved = false;
			mCapturer = iImageCapturer;
			mOnlyLoadCache = onlyLoadCache;
			Bitmap bitmap = mCapturer.get(mContext);
			if ((bitmap != null) && (!bitmap.isRecycled())) {
				setImage(bitmap, animate);
			} else {
				setDefautlImage();
				if (!onlyLoadCache) {
					if (currentTask != null) {
						currentTask.cancel();
						currentTask = null;
					}
					currentTask = new CaptureImageTask(mContext, mCapturer);
					currentTask
							.setOnCompleteHandler(currentTask.new OnCompleteHandler() {

								@Override
								public void onComplete() {
									Bitmap bitmap = mCapturer.get(mContext);
									if (bitmap != null)
										setImage(bitmap, true);
									else
										setDefautlImage();
								}
							});
					threadPool.execute(currentTask);
				}
			}
		}
	}

	private boolean canLoad(Context context) {
		if (mForce)
			return true;
		if ((!TelephoneUtil.isWifiEnable(mContext)) && (SaveFlowMode))
			return false;
		else
			return true;
	}

	private void setImage(Bitmap bitmap, boolean animate) {
		ImageView imageView = (ImageView) mImageViewRef.get();
		if (imageView == null)
			return;
		if (animate) {
			AlphaAnimation animation = new AlphaAnimation(0, 1);
			imageView.setImageBitmap(bitmap);
			imageView.setAnimation(animation);
			animation.setDuration(300L);
			animation.start();
		} else
			imageView.setImageBitmap(bitmap);
		if (finishedHandler != null)
			finishedHandler.onSetImageFinished();
	}

	public ImageView getImageView() {
		return (ImageView) mImageViewRef.get();
	}

	public void setDefautlImage() {
		if (mDefaultDrawable == null)
			return;
		ImageView imageView = (ImageView) mImageViewRef.get();
		if (imageView != null) {
			imageView.setImageDrawable(mDefaultDrawable);
		}

	}

	public abstract interface OnSetImageFinished {
		public abstract void onSetImageFinished();
	}
}
