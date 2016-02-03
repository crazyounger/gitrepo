package com.certusnet.common.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
* 名称: ForbidSlideViewPager.java<br>
* 描述: <br>
* 类型: JAVA<br>
* 最近修改时间:2013-7-29 上午10:52:56<br>
* @since  2013-7-29
* @author lig
*/ 
public class ForbidSlideViewPager extends ViewPager {

	public ForbidSlideViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ForbidSlideViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}

	/**
	 * 设置当前页，立即、没有滑动效果
	 * 
	 * @param item
	 */
	public void setCurrentItemImmediately(int item) {
		super.setCurrentItem(item, false);
	}

}
