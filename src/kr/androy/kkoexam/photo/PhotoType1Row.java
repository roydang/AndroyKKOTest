package kr.androy.kkoexam.photo;

import kr.androy.kkoexam.R;
import kr.androy.kkoexam.model.ThumbPhoto;
import kr.androy.kkoexam.photo.PhotoListAdapter.PhotoRowType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoType1Row implements PhotoRow {

    private final LayoutInflater inflater;
    private final ThumbPhoto photo;
    private int itemResId;
    
    public PhotoType1Row(LayoutInflater inflater, ThumbPhoto photo, int itemResId) {
    	this.inflater = inflater;
    	this.photo = photo;
    	this.itemResId = itemResId;
    }
	@Override
	public View render(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.photo_list_item, null);			
		} else {
			holder = (ViewHolder)convertView.getTag();
		}
		return null;
	}

	@Override
	public int getViewType() {		
		return PhotoRowType.TYPE1.ordinal();
	}

	@Override
	public ThumbPhoto getPhoto() {
		// TODO Auto-generated method stub
		return null;
	}

	private static class ViewHolder {
		TextView title;
		ImageView photo;
	}
}
