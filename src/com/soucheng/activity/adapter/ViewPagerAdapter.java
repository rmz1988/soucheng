package com.soucheng.activity.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.soucheng.view.BehindViewLoader;
import com.soucheng.view.HomeViewLoader;
import com.soucheng.view.MoreViewLoader;
import com.soucheng.view.ReadViewLoader;
import com.soucheng.view.RealEstateViewLoader;
import com.soucheng.view.ViewLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager适配器
 *
 * @author lichen
 */
public class ViewPagerAdapter extends PagerAdapter {

	private List<View> viewList;
	private List<Integer> layoutList;
	private Context context;

	private List<View> rootList = new ArrayList<>();

	public ViewPagerAdapter(Context context, List<View> viewList, List<Integer> layoutList) {
		this.viewList = viewList;
		this.layoutList = layoutList;
		this.context = context;

	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = viewList.get(position);
		if (view == null) {
			view = LayoutInflater.from(context).inflate(layoutList.get(position), null);
			viewList.remove(position);
			viewList.add(position, view);
			switch (position) {
				case 0:
					new HomeViewLoader(this.context, view).load();
					break;
				case 1:
					new RealEstateViewLoader(this.context, view).load();
					break;
				case 2:
					new ReadViewLoader(this.context, view).load();
					break;
				case 3:
					new BehindViewLoader(this.context, view).load();
					break;
				case 4:
					new MoreViewLoader(this.context, view).load();
					break;
			}
		}
		/* else {
			if (rootList.size() > position) {
				view = rootList.get(position);
			} else {
				view = LayoutInflater.from(context).inflate(layoutList.get(position), null);
				rootList.add(view);
				switch (position) {
					case 0:
						new HomeViewLoader(this.context, view).load();
						break;
					case 1:
						new RealEstateViewLoader(this.context, view).load();
						break;
					case 2:
						new ReadViewLoader(this.context, view).load();
						break;
					case 3:
						new BehindViewLoader(this.context, view).load();
						break;
					case 4:
						new MoreViewLoader(this.context, view).load();
						break;
				}
			}
		}*/
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewList.get(position));
	}

	@Override
	public int getCount() {
		return viewList == null ? layoutList.size() : viewList.size();
	}


	@Override
	public boolean isViewFromObject(View view, Object o) {
		return view == o;
	}

}
