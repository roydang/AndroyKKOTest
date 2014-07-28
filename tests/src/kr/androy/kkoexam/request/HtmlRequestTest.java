package kr.androy.kkoexam.request;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import kr.androy.kkoexam.model.ThumbPhoto;
import kr.androy.kkoexam.model.ThumbPhotoList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.navercorp.volleyextensions.mock.ErrorResponseHoldListener;
import com.navercorp.volleyextensions.mock.ResponseHoldListener;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class HtmlRequestTest {
	
	String url = "http://test";
	ResponseHoldListener<ThumbPhotoList> listener = new ResponseHoldListener<ThumbPhotoList>();
	ErrorResponseHoldListener errorListener = new ErrorResponseHoldListener();
	
	@Test
	public void testParseNetworkResponse() throws Exception {

		File file = new File(".");
		System.out.println("File Path=" + file.getAbsolutePath());
		FileReader fr = new FileReader(new File("./test_res/gettyimagesgallery.html"));
		BufferedReader br = new BufferedReader(fr);
		
		StringBuilder sb = new StringBuilder();
		String tmp = null;
		while((tmp=br.readLine())!=null) {
			sb.append(tmp);
		}
		
		
		HtmlRequest<ThumbPhotoList> request = new HtmlRequest<ThumbPhotoList>(url, ThumbPhotoList.class,listener);
		NetworkResponse networkResponse = new NetworkResponse(sb.toString().getBytes());
		Response<ThumbPhotoList> response = request.parseNetworkResponse(networkResponse);
		
		ThumbPhotoList thumbPhotoList = response.result;
		ArrayList<ThumbPhoto> photoArr = thumbPhotoList.getPhotoList();
		
		
		System.out.println("PHOTO thumbPhotoList=" + photoArr);
		
		String photoUrl = null;
		for(ThumbPhoto photo:photoArr) {
			photoUrl = photo.getImageUrl();
			System.out.println("PHOTO URL=" + photoUrl);
			
		}
		assertTrue(photoUrl.startsWith("/Images/Thumbnails"));

	}
	

	
}
 