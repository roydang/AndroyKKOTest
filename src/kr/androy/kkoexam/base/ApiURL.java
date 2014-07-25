package kr.androy.kkoexam.base;

public class ApiURL {
	
	
	public static final String URL_PREFIX = "http://www.gettyimagesgallery.com";
	
	public enum EXAM_URL {
		
		PHOTO_LIST ("/collections/archive/slim-aarons.aspx");
		
		private String url;
		private EXAM_URL(String url) {
			this.url = url;
		}
		public String getURL() {
			return URL_PREFIX + url;
		}		
	}
	
	public static String getPhotoUrl(String url) {
		return URL_PREFIX + url;
	}
}
