package kr.androy.kkoexam.request;

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
		// Given
//		String content =
//		"<news>\n" + 
//		 "   <imageUrl>http://static.naver.com/volley-ext.jpg</imageUrl>\n" + 
//		 "   <title>Volley extention has released</title>\n" + 
//		 "</news>";
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
		
		for(ThumbPhoto photo:photoArr) {
			System.out.println("PHOTO URL=" + photo.getImageUrl());
		}
		
		// When
//		Response<News> response = request.parseNetworkResponse(networkResponse);
//		// Then
//		News news = response.result;
//		assertThat(news.imageUrl, is("http://static.naver.com/volley-ext.jpg"));
//		assertThat(news.title, is("Volley extention has released"));
	}
	

	
}
 