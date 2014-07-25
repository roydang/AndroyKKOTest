package kr.androy.kkoexam.photo;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.ImageLoader;

import kr.androy.kkoexam.KKOApplication;
import kr.androy.kkoexam.R;
import kr.androy.kkoexam.model.ThumbPhoto;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PhotoListAdapter extends BaseAdapter {

	enum PhotoRowType{
		TYPE0(0),TYPE1(1);
		
		private int type;
		private PhotoRowType(int type) {
			this.type = type;
		}
		public int getTypeNum() {
	    	return this.type;
	    }
	}
	private Context context;
	private List<PhotoRow> rows;
//	private ImageLoader imageLoader;

	public PhotoListAdapter(Context context){
		this.context = context;
		this.rows = new ArrayList<PhotoRow>();
//		this.imageLoader = KKOApplication.getImageLoader();
	}
	public void setPhotoList(List<ThumbPhoto> photoList) {

		LayoutInflater inflater = LayoutInflater.from(context);
		
		for (ThumbPhoto photo : photoList) {
			if(photo.getType() == PhotoRowType.TYPE0.getTypeNum()) {
				rows.add(new PhotoType0Row(inflater, photo, R.layout.photo_list_item_type_0));
			} else if (photo.getType() == PhotoRowType.TYPE1.getTypeNum()) {
				rows.add(new PhotoType1Row(inflater, photo, R.layout.photo_list_item_type_1));
			}
		}
		notifyDataSetChanged();
	}
	@Override
	public int getViewTypeCount() {	
		return PhotoRowType.values().length;
	}
	@Override
	public int getItemViewType(int position) {		
		return rows.get(position).getViewType();
	}
	@Override
	public int getCount() {
		return rows.size();
	}

	@Override
	public Object getItem(int position) {
		return rows.get(position-1);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return rows.get(position).render(position, convertView, parent);
	}

	
}
