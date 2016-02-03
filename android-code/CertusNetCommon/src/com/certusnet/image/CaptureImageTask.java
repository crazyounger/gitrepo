package com.certusnet.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

public class CaptureImageTask implements Runnable {

	private static final int BITMAP_READY = 0;
	private Context mContext;
	private IImageCapturer imageCapturer;
	private OnCompleteHandler mOnCompleteHandler;

	public CaptureImageTask(Context context, IImageCapturer capturer) {
		this.mContext=context;
		this.imageCapturer=capturer;
	}

	@Override
	public void run() {
		if (this.imageCapturer != null) {
			Bitmap bitmap = this.imageCapturer.request(this.mContext);
			complete(bitmap);
			this.mContext = null;
		}
	}

	public void complete(Bitmap bitmap) {
		if ((this.mOnCompleteHandler != null) && (bitmap != null))
			this.mOnCompleteHandler.obtainMessage(BITMAP_READY, bitmap)
					.sendToTarget();
	}

	public IImageCapturer getImage() {
		return this.imageCapturer;
	}

	public void cancel() {
		this.mOnCompleteHandler.cancel();
	}

	public void setOnCompleteHandler(OnCompleteHandler onCompleteHandler) {
		this.mOnCompleteHandler = onCompleteHandler;
	}

	public class OnCompleteHandler extends Handler {
		private boolean cancelled = false;

		public void cancel() {
			this.cancelled = true;
		}

		public void handleMessage(Message paramMessage) {
			if (this.cancelled)
				return;
			onComplete();
		}

		public void onComplete() {
		}
	}

}
