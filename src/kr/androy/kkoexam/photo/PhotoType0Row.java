package kr.androy.kkoexam.photo;

import kr.androy.kkoexam.KKOApplication;
import kr.androy.kkoexam.R;
import kr.androy.kkoexam.base.ApiURL;
import kr.androy.kkoexam.model.ThumbPhoto;
import kr.androy.kkoexam.photo.PhotoListAdapter.PhotoRowType;
import kr.androy.volleyext.base.util.display.DisplayUtility;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.NetworkImageView;

public class PhotoType0Row implements PhotoRow {

    private final LayoutInflater inflater;
    private final ThumbPhoto photo;
    private int itemResId;
    private ImageLoader imageLoader;
    
    public PhotoType0Row(LayoutInflater inflater, ThumbPhoto photo, int itemResId) {
    	this.inflater = inflater;
    	this.photo = photo;
    	this.itemResId = itemResId;
    	this.imageLoader = KKOApplication.getImageLoader();
    }
	@Override
	public View render(int position, View convertView, ViewGroup parent) {

		final ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(itemResId, null);
			holder = new ViewHolder();
			holder.title = (TextView)convertView.findViewById(R.id.title_txt);
			holder.photo = (NetworkImageView)convertView.findViewById(R.id.img_view);
			holder.urlText  = (TextView)convertView.findViewById(R.id.file_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		
		ThumbPhoto photo = getPhoto();
		holder.title.setText(photo.getTitle());
		holder.urlText.setText(photo.getImageUrl());
		holder.photo.setDefaultImageResId(R.drawable.icon_pic);
		holder.photo.setErrorImageResId(R.drawable.pau_no);
		holder.photo.setImageUrl(ApiURL.getPhotoUrl(photo.getImageUrl()), imageLoader);
		
		
		return convertView;
	}

	@Override
	public int getViewType() {		
		return PhotoRowType.TYPE0.ordinal();
	}

	@Override
	public ThumbPhoto getPhoto() {
		return this.photo;
	}

	private static class ViewHolder {
		TextView title;
		TextView urlText;
		NetworkImageView photo;
	}
}
