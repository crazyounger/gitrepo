package com.certusnet.common.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.airshiplay.mobile.log.Logger;
import com.airshiplay.mobile.log.LoggerFactory;

/**
 * 条件决定滑动 ViewPager<br/>
 * 名称: ConditionSlidViewPager.java<br>
 * 描述: <br>
 * 类型: JAVA<br>
 * 最近修改时间:2013-9-3 上午10:16:55<br>
 * 
 * @since 2013-9-3
 * @author lig
 */
public class ConditionSlidViewPager extends ViewPager {
	private boolean prohibitRightSliding;
	private boolean prohibitLeftSliding;
	private OnStartSlidListener startSlidListener;
	private Logger log = LoggerFactory.getLogger(getClass());

	public ConditionSlidViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ConditionSlidViewPager(Context context) {
		super(context);
	}

	@Override
	public void scrollTo(int x, int y) {
		int deltaX = x - getScrollX();
		if (this.startSlidListener != null) {
			boolean prohibit = this.startSlidListener.onProhibitSlid(deltaX);
			log.debug("deltaX=%s,prohibit=%s", deltaX, prohibit);
			if (prohibit)
				return;
		}
		if (prohibitLeftSliding && prohibitRightSliding) {
			return;
		} else if (prohibitLeftSliding) {
			if (deltaX < 0)
				return;
		} else if (prohibitRightSliding) {
			if (deltaX > 0)
				return;
		}
		super.scrollTo(x, y);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return super.onTouchEvent(arg0);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return super.onInterceptTouchEvent(arg0);
	}

	/**
	 * 禁止右滑动
	 */
	public void prohibitRightSlid() {
		prohibitRightSliding = true;
		prohibitLeftSliding = false;
	}

	/**
	 * 禁止左滑动
	 */
	public void prohibitLeftSlid() {
		prohibitRightSliding = false;
		prohibitLeftSliding = true;
	}

	/**
	 * 禁止滑动
	 */
	public void prohibitSlid() {
		prohibitRightSliding = true;
		prohibitLeftSliding = true;
	}

	/**
	 * 允许滑动
	 */
	public void allowSlid() {
		prohibitRightSliding = false;
		prohibitLeftSliding = false;
	}

	public void setOnStartSlidListener(OnStartSlidListener startSlidListener) {
		this.startSlidListener = startSlidListener;
	}

	public static interface OnStartSlidListener {
		/**
		 * 决定是否禁止滑动
		 * 
		 * @param direction
		 *            >0 右滑
		 * @return true，禁止滑动，false 滑动
		 * @变更记录 2013-9-3 上午10:03:11 lig
		 * 
		 */
		public boolean onProhibitSlid(int direction);
	}
}
