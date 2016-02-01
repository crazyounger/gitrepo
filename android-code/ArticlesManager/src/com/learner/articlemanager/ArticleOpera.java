
package com.learner.articlemanager;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class ArticleOpera {

	private static final String  TAG = "ArticleAdapter" ;
	
	private ContentResolver resolver ;

	private Context mContext;
	
	public ArticleOpera(Context context){
		this.mContext = context ;
		resolver = mContext.getContentResolver() ;
	}
	
	public long insertArticle(Article article){
		ContentValues values = new ContentValues() ;
		values.put(Articles.TITLE,article.getTitle());
		values.put(Articles.ABSTRACT, article.getAbs());
		values.put(Articles.URL, article.getUrl());
		
		Uri uri = resolver.insert(Articles.CONTENT_URI, values);
		String itemId = uri.getPathSegments().get(1);
		
		return Integer.valueOf(itemId).longValue();
	}
	
	public boolean updateArticle(Article article){
		Uri uri = ContentUris.withAppendedId(Articles.CONTENT_URI, article.getId());
		
		ContentValues values = new ContentValues() ;
		values.put(Articles.TITLE,article.getTitle());
		values.put(Articles.ABSTRACT, article.getAbs());
		values.put(Articles.URL, article.getUrl());
		
		int count = resolver.update(uri, values, null, null);
		return count > 0  ;
	}
	
	public boolean removeArticle(int id){
		Uri uri = ContentUris.withAppendedId(Articles.CONTENT_URI, id);
		int count  = resolver.delete(uri,null ,null);
		return count > 0 ;
	}
	
	public ArrayList<Article> queryAllArticles(){
		ArrayList<Article> list = new ArrayList<Article>() ;
		String [] projection = new String []{
			Articles.ID,Articles.TITLE,Articles.ABSTRACT,Articles.URL	
		};
		Cursor cursor = resolver.query(Articles.CONTENT_URI	, projection,null,null,null);
		if(cursor != null && cursor.moveToFirst()){
			do{
				int id = cursor.getInt(cursor.getColumnIndex(Articles.ID));
				String title = cursor.getString(cursor.getColumnIndex(Articles.TITLE));
				String abs = cursor.getString(cursor.getColumnIndex(Articles.ABSTRACT));
				String url = cursor.getString(cursor.getColumnIndex(Articles.URL));
				
				Article article = new Article(id, title, abs, url);
				list.add(article);
			}while(cursor.moveToNext());
			return list ;
		}
		return null ;
	}
	
	public int getArticlesCount(){
		String [] projection = new String []{
				Articles.ID,Articles.TITLE,Articles.ABSTRACT,Articles.URL	
			};
		Cursor cursor = resolver.query(Articles.CONTENT_URI	, projection,null,null,null);
		if(cursor!=null){
			return cursor.getCount() ;
		}
		return -1 ;
	}

	public Article getArticleById(int id ){
		Uri uri = ContentUris.withAppendedId(Articles.CONTENT_URI, id);
		String [] projection = new String []{
			Articles.ID ,Articles.TITLE,Articles.ABSTRACT,Articles.URL	
		};
		Cursor cursor = resolver.query(uri, projection, null, null, null) ;
		if(cursor!=null && cursor.moveToFirst()){
			int articleId = cursor.getInt(cursor.getColumnIndex(Articles.ID));
			String title = cursor.getString(cursor.getColumnIndex(Articles.TITLE));
			String abString = cursor.getString(cursor.getColumnIndex(Articles.ABSTRACT));
			String url = cursor.getString(cursor.getColumnIndex(Articles.URL));
			
			Article article = new Article(id,title,abString,url);
			return article ;
		}
		return null ;
	}
	
	public Article getArticleByPos(int pos) {  
        Uri uri = ContentUris.withAppendedId(Articles.CONTENT_POS_URI, pos);  

        String[] projection = new String[] {  
                        Articles.ID,  
            Articles.TITLE,  
            Articles.ABSTRACT,  
            Articles.URL  
        };  

        Cursor cursor = resolver.query(uri, projection, null, null, Articles.DEFAULT_SORT_ORDER);  
        if (!cursor.moveToFirst()) {  
                return null;  
        }  

        int id = cursor.getInt(0);  
        String title = cursor.getString(1);  
        String abs = cursor.getString(2);  
        String url = cursor.getString(3);  

        return new Article(id, title, abs, url);  
}  
}
