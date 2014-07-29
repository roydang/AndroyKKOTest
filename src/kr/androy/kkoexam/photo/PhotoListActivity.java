package kr.androy.kkoexam.photo;

import kr.androy.kkoexam.KKOApplication;
import kr.androy.kkoexam.R;
import kr.androy.kkoexam.base.ApiURL;
import kr.androy.kkoexam.model.ThumbPhotoList;
import kr.androy.kkoexam.request.HtmlRequest;
import kr.androy.volleyext.adpter.VExtOnScrollListener;
import kr.androy.volleyext.base.util.log.Logger;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;

public class PhotoListActivity extends Activity {

	private static final Logger logger = Logger.getLogger(PhotoListActivity.class);
	
	private RequestQueue requestQueue;	
	private PhotoListAdapter listAdapter;
	
	private ListView listView;
	private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);    
        
        init();
        
        loadPhotoList();
    }
    
    private void init() {
    	requestQueue = KKOApplication.getRequestQueue();
    	
        listView = (ListView)findViewById(R.id.list_view);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        listView.setOnScrollListener(new VExtOnScrollListener(requestQueue, true, true));
        listAdapter = new PhotoListAdapter(PhotoListActivity.this);
        
        listView.setAdapter(listAdapter);
    }
    
    public void loadPhotoList() {
    	HtmlRequest<ThumbPhotoList> request = new HtmlRequest<ThumbPhotoList>(ApiURL.EXAM_URL.PHOTO_LIST.getURL(), ThumbPhotoList.class, 
			new Listener<ThumbPhotoList>() {

				@Override
				public void onResponse(ThumbPhotoList photoList) {
					logger.d("ThumbPhotoList photoList:%s", photoList);
					listAdapter.setPhotoList(photoList.getPhotoList());
					progressBar.setVisibility(View.GONE);
				}
    		
	    	}, new ErrorListener() {
	
				@Override
				public void onErrorResponse(VolleyError arg0) {
				}
	    		
			});
    	request.setShouldCache(true);
    	requestQueue.add(request);
    }
}
