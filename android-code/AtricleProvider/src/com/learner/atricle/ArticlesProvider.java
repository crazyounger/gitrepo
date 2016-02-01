package com.learner.atricle;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

public class ArticlesProvider extends ContentProvider{

	private static final String TAG = "com.learner.article.ArticlesProvider" ;
	
	public static final String DB_NAME = "Articles.db" ;
	public static final String DB_TABLE = "ArticleTable" ;
	public static final int DB_VERSION = 1 ;
	
	public static final String DB_CREATE = "create table " + DB_TABLE + 
			            " ( " + Articles.ID + " integer primary key autoincrement," +
			            Articles.TITLE + " text not null , " +
			            Articles.ABSTRACT + " text not null ," + 
			            Articles.URL + " text );";
	
	private static final UriMatcher uriMatcher ;
	
	static {
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH) ;
		uriMatcher.addURI(Articles.AUTHORITY, "item", Articles.ITEM);
		uriMatcher.addURI(Articles.AUTHORITY, "item/#", Articles.ITEM_ID);
		uriMatcher.addURI(Articles.AUTHORITY, "pos/#",Articles.ITEM_POS);
	}
	
	private static final HashMap<String, String> articleProjectionMap ;
	static {
		articleProjectionMap = new HashMap<String, String>() ;
		articleProjectionMap.put(Articles.ID, Articles.ID);
		articleProjectionMap.put(Articles.TITLE, Articles.TITLE);
		articleProjectionMap.put(Articles.ABSTRACT, Articles.ABSTRACT);
		articleProjectionMap.put(Articles.URL, Articles.URL);
	}
	
	private DBHelper dbHelper ;
	private ContentResolver contentResolver ;
	
		
	@Override
	public boolean onCreate() {
		Context context = getContext() ;
		contentResolver  = context.getContentResolver() ;
		dbHelper = new DBHelper(context,DB_NAME,null,DB_VERSION);
		Log.d(TAG, "Articles table create ") ;
		return true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Log.d(TAG, "ArticleProvider query : " + uri );
		
		SQLiteDatabase db = dbHelper.getReadableDatabase() ;
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder() ;
		String limit = null ;
		switch ( uriMatcher.match(uri)) {
		case Articles.ITEM:
			builder.setTables(DB_TABLE);
			builder.setProjectionMap(articleProjectionMap);
			break;
		case Articles.ITEM_ID:
			String id = uri.getPathSegments().get(1);
			builder.setTables(DB_TABLE);
			builder.setProjectionMap(articleProjectionMap);
			builder.appendWhere(Articles.ID + " = "+id);
			break;
		case Articles.ITEM_POS:
			String poString = uri.getPathSegments().get(1);
			builder.setTables(DB_TABLE);
			builder.setProjectionMap(articleProjectionMap);
			limit = poString + " , 1 ";
			break;
		default:
			throw new IllegalArgumentException("Error uri :" + uri );
		}
		Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, 
				TextUtils.isEmpty(sortOrder) ? Articles.DEFAULT_SORT_ORDER : sortOrder,limit);
		cursor.setNotificationUri(contentResolver, uri);
		return cursor;
	}

	@Override
	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case Articles.ITEM:
			return Articles.CONTENT_TYPE ;
		case Articles.ITEM_ID:
		case Articles.ITEM_POS:
			return Articles.CONTENT_ITEM_TYPE ; 
		default:
			throw new IllegalArgumentException("Error uri : " + uri ) ;
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		if(uriMatcher.match(uri) != Articles.ITEM){
			throw new IllegalArgumentException("Error uri : " + uri );
		}
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		long id = db.insert(DB_TABLE, Articles.ID, values) ;
		
		if(id < 0 ){
			throw new SQLException("unable to insert " + values  + "for" + uri );
		}
		Uri newUri = ContentUris.withAppendedId(uri, id) ;
		contentResolver.notifyChange(newUri, null);
		return newUri;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase() ;
		int count = 0 ;
		
		switch (uriMatcher.match(uri)) {
		case Articles.ITEM:
			count = db.delete(DB_TABLE, selection, selectionArgs);
			break;
		case Articles.ITEM_ID:
			String id = uri.getPathSegments().get(1);
			count = db.delete(DB_TABLE,Articles.ID + "=" + id  
                    + (!TextUtils.isEmpty(selection) ? " and (" + selection + ')' : "") , selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("error uri : "+ uri );
		}
		contentResolver.notifyChange(uri, null);
		return count;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase() ;
		int count = 0 ;
		
		switch (uriMatcher.match(uri)) {
		case Articles.ITEM:
			count = db.update(DB_TABLE, values, selection, selectionArgs);
			break;
		case Articles.ITEM_ID:
			String id = uri.getPathSegments().get(1) ;
			count = db.update(DB_TABLE, values, Articles.ID + "=" + id  
                    + (!TextUtils.isEmpty(selection) ? " and (" + selection + ')' : ""), selectionArgs);
			break;
		default:
			throw new IllegalArgumentException("Error uri : "+uri );
		}
		contentResolver.notifyChange(uri, null);
		return count;
	}

	@Override
	public Bundle call(String method, String arg, Bundle extras) {
		Log.d(TAG, "ArticlesProvider.call " + method);
		if(method.equals(Articles.METHOD_GET_ITEM_COUNT)){
			return getItemCount();
		}
		throw new IllegalArgumentException("error method call : "+method);
		
	}

	private Bundle getItemCount() {
		Log.d(TAG, "ArticlesProvider.getItemCount");
		SQLiteDatabase db = dbHelper.getReadableDatabase() ;
		Cursor cursor = db.rawQuery("select count(*) from " + DB_TABLE,null);
		int count = 0 ;
		if(cursor != null){
			if(cursor.moveToFirst()){
				count = cursor.getInt(0);
			}
		}
		Bundle bundle = new Bundle() ;
		bundle.putInt(Articles.KEY_ITEM_COUNT, count);
		
		cursor.close();
		db.close();
		
		return bundle;
	}
}
