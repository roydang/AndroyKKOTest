package kr.androy.kkoexam.photo;

import java.util.List;

import kr.androy.kkoexam.model.ThumbPhoto;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class PhotoListAdapter extends BaseAdapter {

	enum PhotoRowType{
		TYPE1(1),TYPE2(2);
		
		private int type;
		private PhotoRowType(int type) {
			this.type = type;
		}
		public int getTypeNum() {
	    	return this.type;
	    }
	}
	private int itemResId;
	private Context context;
	private List<ThumbPhoto> photoList;
	private List<PhotoRow> rows;
	
	public PhotoListAdapter(Context context, int itemResId){
		this.itemResId = itemResId;
		this.context = context;
	}
	public void setPhotoList(List<ThumbPhoto> photoList) {
		this.photoList = photoList;
		
		for (ThumbPhoto photo : photoList) {
			if(photo.getType() == PhotoRowType.TYPE1.getTypeNum()) {
				
			} else if (photo.getType() == PhotoRowType.TYPE2.getTypeNum()) {
				
			}
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
