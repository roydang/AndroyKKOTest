package kr.androy.kkoexam.photo;

import kr.androy.kkoexam.model.ThumbPhoto;
import android.view.View;
import android.view.ViewGroup;

public interface PhotoRow {
	public View render(int position, View convertView, ViewGroup parent);
	public int getViewType();
	public ThumbPhoto getPhoto();
}
