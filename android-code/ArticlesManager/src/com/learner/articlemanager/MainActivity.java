package com.learner.articlemanager;

import java.util.Observer;

import com.leaner.articlesmanager.R;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener, OnItemClickListener{

	
	private static final int ADD_ARTICLE = 0x0001;
	private static final int EDIT_ARTICAL_ACTIVITY = 0x0002;
	
	private ListView mListView;
	private Button mAddBtn;

	private ContentObserver observer;
	
	private ArticleAdapter mAdapter ;
	private ArticleOpera mArticleOpera ;
	
	protected void onCreate(android.os.Bundle saveInstanceBundle) {
		super.onCreate(saveInstanceBundle);
		setContentView(R.layout.activity_main);
		initViews();
	}

	private void initViews() {
		mListView = (ListView)findViewById(R.id.listview);
		mAddBtn = (Button)findViewById(R.id.btn_add);
		mArticleOpera = new ArticleOpera(this);
		
		
		mAddBtn.setOnClickListener(this);
		mAdapter = new ArticleAdapter(this);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		
		
		observer = new ArticleObserver(new Handler());
		getContentResolver().registerContentObserver(Articles.CONTENT_URI, true, observer);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:
			addArticles();
			break;

		default:
			break;
		}
	}

	private void addArticles() {
		Intent intent = new Intent(this, ArticleActivity.class);
		startActivityForResult(intent,ADD_ARTICLE);
	};
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		getContentResolver().unregisterContentObserver(observer);
	}
	
	private class ArticleObserver extends ContentObserver{

		public ArticleObserver(Handler handler) {
			super(handler);
		}
		
		@Override
		public void onChange(boolean selfChange) {
			mAdapter.notifyDataSetChanged();
		}
		
	}
	
	private class ArticleAdapter extends BaseAdapter{
		private Context mContext ;
		private LayoutInflater mInflater ;
		
		public ArticleAdapter() { }
		
		public ArticleAdapter(Context context){
			mContext = context ;
			mInflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return mArticleOpera.getArticlesCount();
		}

		@Override
		public Object getItem(int position) {
			return mArticleOpera.getArticleByPos(position);  
		}

		@Override
		public long getItemId(int position) {
			return mArticleOpera.getArticleByPos(position).getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			 Article article = (Article)getItem(position);  
			  
             if (convertView == null) {  
                     convertView = mInflater.inflate(R.layout.item, null);  
             }  

             TextView titleView = (TextView)convertView.findViewById(R.id.textview_article_title);  
             titleView.setText("Title: " + article.getTitle());  

             TextView abstractView = (TextView)convertView.findViewById(R.id.textview_article_abstract);  
             abstractView.setText("Abstract: " + article.getAbs());  

             TextView urlView = (TextView)convertView.findViewById(R.id.textview_article_url);  
             urlView.setText("URL: " + article.getUrl());  

             return convertView;  
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		 Intent intent = new Intent(this, ArticleActivity.class);  
		  
         Article article = mArticleOpera.getArticleByPos(position);  
         intent.putExtra(Articles.ID, article.getId());  
         intent.putExtra(Articles.TITLE, article.getTitle());  
         intent.putExtra(Articles.ABSTRACT, article.getAbs());  
         intent.putExtra(Articles.URL, article.getUrl());  

         startActivityForResult(intent, EDIT_ARTICAL_ACTIVITY);  		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case ADD_ARTICLE: {
			if (resultCode == FragmentActivity.RESULT_OK) {
				String title = data.getStringExtra(Articles.TITLE);
				String abs = data.getStringExtra(Articles.ABSTRACT);
				String url = data.getStringExtra(Articles.URL);

				Article article = new Article(-1, title, abs, url);
				mArticleOpera.insertArticle(article);
			}

			break;
		}

		case EDIT_ARTICAL_ACTIVITY: {
			if (resultCode == FragmentActivity.RESULT_OK) {
				int action = data.getIntExtra(
						ArticleActivity.EDIT_ARTICLE_ACTION, -1);
				if (action == ArticleActivity.MODIFY_ARTICLE) {
					int id = data.getIntExtra(Articles.ID, -1);
					String title = data.getStringExtra(Articles.TITLE);
					String abs = data.getStringExtra(Articles.ABSTRACT);
					String url = data.getStringExtra(Articles.URL);

					Article article = new Article(id, title, abs, url);
					mArticleOpera.updateArticle(article);
				} else if (action == ArticleActivity.DELETE_ARTICLE) {
					int id = data.getIntExtra(Articles.ID, -1);

					mArticleOpera.removeArticle(id);
				}

			}

			break;
		}
		}
	}
}
