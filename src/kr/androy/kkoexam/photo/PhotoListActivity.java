package kr.androy.kkoexam.photo;

import kr.androy.kkoexam.KKOApplication;
import kr.androy.kkoexam.R;
import kr.androy.kkoexam.R.id;
import kr.androy.kkoexam.R.layout;
import kr.androy.kkoexam.model.ThumbPhotoList;
import kr.androy.kkoexam.request.HtmlRequest;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

public class PhotoListActivity extends Activity {

	private String url = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx";
	
	private RequestQueue requestQueue;
	private ImageLoader imageLoader;
	
	private ListView listView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);    
        
        requestQueue = KKOApplication.getRequestQueue();
        imageLoader = KKOApplication.getImageLoader();
        
        listView = (ListView)findViewById(R.id.list_view);
        
        loadPhotoList();
    }
    
    public void loadPhotoList() {
    	HtmlRequest<ThumbPhotoList> request = new HtmlRequest<ThumbPhotoList>(url, ThumbPhotoList.class, 
			new Listener<ThumbPhotoList>() {

				@Override
				public void onResponse(ThumbPhotoList arg0) {
					// TODO Auto-generated method stub
					
				}
    		
	    	}, new ErrorListener() {
	
				@Override
				public void onErrorResponse(VolleyError arg0) {
					// TODO Auto-generated method stub
					
				}
	    		
			});
    	requestQueue.add(request);
    }
}
