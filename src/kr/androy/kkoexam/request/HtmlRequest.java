package kr.androy.kkoexam.request;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import kr.androy.kkoexam.model.ThumbPhotoList;
import kr.androy.kkoexam.model.ThumbPhoto;
import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.navercorp.volleyextensions.request.AbstractConverterRequest;

public class HtmlRequest<T> extends AbstractConverterRequest<T> {

	
	
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
            	System.out.println("Ele=>" + element);
            	
//            	List<Element> srcList = element.getAllElements("src",Pattern.compile("/Images/Thumbnails/[0-9]+/[0-9]+.jpg"));
//            	for (Element srcEle:srcList) {
//            		System.out.println("Ele srcEle=>" + srcEle);
//            	}
            	
            	List<Element> aList = element.getAllElements(HTMLElementName.A);
            	
         		ThumbPhoto photo = new ThumbPhoto();
            	for (Element aEle:aList) {
            		List<Element> childEle = aEle.getChildElements();
            		
            		if (childEle.size() > 0) {
            			Element imgEle = childEle.get(0);  
            			String srcStr = imgEle.getAttributeValue("src");
            			photo.setImageUrl(srcStr);
            			System.out.print("imgSrc=>" + srcStr);
            		} else {
            			TextExtractor ext = aEle.getTextExtractor();
            			photo.setTitle(ext.toString());
            			System.out.println("ext=>" + ext);
            		}
            	}
        		photoList.add(photo);
            }
			ThumbPhotoList photoListObj = new ThumbPhotoList();
			photoListObj.setPhotoList(photoList);
//			Object o = photoList;
			
			
            return Response.success((T)photoListObj, HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (Exception e) {
			return Response.error(new ParseError(e));
		}

	}

//	@Override
//	protected Response<T> parseNetworkResponse(NetworkResponse response) {

//		String charset = HttpHeaderParser.parseCharset(response.headers);
//		Reader reader = null;
//		try {
//			reader = new InputStreamReader(new ByteArrayInputStream(response.data), charset);
//			T result = persister.read(getTargetClass(),	reader);
//			return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
//			
//		} catch (UnsupportedEncodingException e) {
//			return Response.error(new ParseError(e));
//		} catch (XmlPullParserException e) {
//			return Response.error(new ParseError(e));
//		} catch (ElementException e) {
//			return Response.error(new ParseError(e));
//		} catch (Exception e) {
//			return Response.error(new VolleyError(e));
//		} finally {
//			IoUtils.closeQuietly(reader);
//		}
//	}

}
