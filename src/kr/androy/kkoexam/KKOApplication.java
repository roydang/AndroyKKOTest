package kr.androy.kkoexam;

import java.io.File;

import kr.androy.volleyext.base.AndroyApplication;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.NoCache;
import com.navercorp.volleyextensions.cache.universalimageloader.disc.impl.UniversalUnlimitedDiscCache;
import com.navercorp.volleyextensions.cache.universalimageloader.memory.impl.UniversalLimitedAgeMemoryCache;
import com.navercorp.volleyextensions.cache.universalimageloader.memory.impl.UniversalLruMemoryCache;

public class KKOApplication extends AndroyApplication {

	private static final int DEFAULT_CACHE_SIZE = 10 * 1024 * 1024;
	private static final long DEFAULT_MAX_AGE = 60;
	private static final String CACHE_FOLDER = "/test-universal";
	private static KKOApplication application;
	@Override
	public void onCreate() {
		Log.d("unknown","## MyApplication init");
		super.onCreate();
		com.android.volley.VolleyLog.DEBUG  = false;
		application = this;
	}
	public static KKOApplication getApplication() {
		return application;
	}
	@Override
	protected Cache getDefaultDiskCache(Context context) {
		File cacheDir = new File(context.getCacheDir().getPath() + CACHE_FOLDER);
		if (cacheDir == null) {
			return new NoCache();
		}

		return new UniversalUnlimitedDiscCache(cacheDir);
	}

	@Override
	protected ImageCache getDefaultImageCache(Context context) {
		return new UniversalLimitedAgeMemoryCache(new UniversalLruMemoryCache(DEFAULT_CACHE_SIZE), DEFAULT_MAX_AGE);
	}

	@Override
	protected Network getDefaultNetwork() {
		
		
		  HttpStack stack;
		  if (Build.VERSION.SDK_INT >= 9) {
              stack = new HurlStack();
          } else {
              // Prior to Gingerbread, HttpUrlConnection was unreliable.
              // See: http://android-developers.blogspot.com/2011/09/androids-http-clients.html
              stack = new HttpClientStack(AndroidHttpClient.newInstance(getUserAgent()));
          }
		return new BasicNetwork(stack);
	}
	@Override
	public String getUserAgent() {
		String userAgent = "volley/0";
        try {
            String packageName = getApplication().getPackageName();
            PackageInfo info = getApplication().getPackageManager().getPackageInfo(packageName, 0);
            userAgent = packageName + "/" + info.versionCode;
        } catch (NameNotFoundException e) {
        }
	    return userAgent;    
	}
}
