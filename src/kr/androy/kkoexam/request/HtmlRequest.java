package kr.androy.kkoexam.request;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import kr.androy.kkoexam.model.ThumbPhoto;
import kr.androy.kkoexam.model.ThumbPhotoList;
import kr.androy.volleyext.base.util.log.Logger;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;
import android.os.Environment;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.navercorp.volleyextensions.request.AbstractConverterRequest;

public class HtmlRequest<T> extends AbstractConverterRequest<T> {

	private static final Logger logger = Logger.getLogger(HtmlRequest.class);
	
	public HtmlRequest(String url, Class<T> clazz, Listener<T> listener) {
		super(url, clazz, listener);
	}
	public HtmlRequest(String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(url, clazz, listener, errorListener);
	}
	public HtmlRequest(int method, String url, Class<T> clazz, Listener<T> listener, ErrorListener errorListener) {
		super(method, url, clazz, listener, errorListener);
	}
	@Override
	protected Response<T> parseNetworkResponse(NetworkResponse response) {
		
		String hrefPrefix = "/Images/Thumbnails/";
		String rootClassName = "gallery-item-group exitemrepeater";
		
		ArrayList<ThumbPhoto> photoList = new ArrayList<ThumbPhoto>();
		
		try {
			Source source = new Source(getBodyString(response));		
			
			List<Element> list = source.getAllElementsByClass(rootClassName);		
      
            for (int i = 0; i < list.size(); i++) {
            	Element element = list.get(i);            

            	List<Element> aList = element.getAllElements(HTMLElementName.A);
            	
         		ThumbPhoto photo = new ThumbPhoto();
         		int itemType = 1;
            	for (Element aEle:aList) {
            		List<Element> childEle = aEle.getChildElements();
            		
            		if (childEle.size() > 0) {
            			Element imgEle = childEle.get(0);  
            			String srcStr = imgEle.getAttributeValue("src");
            			photo.setImageUrl(srcStr);

            		} else {
            			TextExtractor ext = aEle.getTextExtractor();
            			String title = ext.toString();
            			photo.setTitle(title);

            		}
            	}
    			itemType = i%2;
    			logger.d("# itemType:%s", itemType);
    			photo.setType(itemType);
        		photoList.add(photo);
        		
        		
            }
			ThumbPhotoList photoListObj = new ThumbPhotoList();
			photoListObj.setPhotoList(photoList);
			
			
            return Response.success((T)photoListObj, HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}

	}


}
