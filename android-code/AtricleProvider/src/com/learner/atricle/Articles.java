package com.learner.atricle;

import android.net.Uri;


/**
 * 定义常量，访问文章的URI，MIME类型及格式
 * @author zhaopf
 *
 */
public class Articles {

	/** data field */
	public static final String ID = "_id" ;
	public static final String TITLE = "_title";
	public static final String ABSTRACT = "_abstract" ;
	public static final String URL = "_url" ;
	
	public static final String DEFAULT_SORT_ORDER = "_id asc" ;
	
	public static final String METHOD_GET_ITEM_COUNT = "method_get_item_count";
	public static final String KEY_ITEM_COUNT = "key_item_count" ;
	
	public static final String AUTHORITY = "com.learner.provider.atricles" ;
	
	public static final int ITEM = 1 ;
	public static final int ITEM_ID = 2 ;
	public static final int ITEM_POS = 3 ;
	
	/** mime */
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.learner.atricle" ;
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.learner.atricle" ;
	
	/** content uri */
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/item") ;
	public static final Uri CONTENT_POS_URI = Uri.parse("content://" + AUTHORITY + "/pos") ;
	
}
