package kr.androy.kkoexam;

import kr.androy.kkoexam.photo.PhotoListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Implements;

import android.os.SystemClock;

import com.android.volley.VolleyLog;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	private PhotoListActivity activity;
	
	@Before
	public void setup()  {
		VolleyLog.DEBUG = true;
		activity = Robolectric.buildActivity(PhotoListActivity.class).create().get();
	}
	
	@Test
	@Config( shadows = {MyShadowSystemClock.class})
	public void testLoadPhotoList() {
		
		activity.loadPhotoList();
		System.out.println("end of test-------");
	}
	
	@Implements(value = SystemClock.class, callThroughByDefault = true)
	public static class MyShadowSystemClock {
	    public static long elapsedRealtime() {
	        return 0;
	    }
	}
}
