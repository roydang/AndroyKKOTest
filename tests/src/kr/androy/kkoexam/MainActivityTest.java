package kr.androy.kkoexam;

import kr.androy.kkoexam.photo.PhotoListActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {
	private PhotoListActivity activity;
	
	@Before
	public void setup()  {
		activity = Robolectric.buildActivity(PhotoListActivity.class).create().get();
	}
	
	@Test
	public void testLoadPhotoList() {
		activity.loadPhotoList();
	}
}
