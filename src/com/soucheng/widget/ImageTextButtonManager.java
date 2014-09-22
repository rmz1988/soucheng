package com.soucheng.widget;

import android.content.Context;
import com.soucheng.activity.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 导航按钮管理器
 *
 * @author lichen
 */
public class ImageTextButtonManager {

	private Map<Integer, ImageTextButton> btnMap = new HashMap<>();
	private Map<Integer, ImageTextButton> viewBtnMap = new HashMap<>();

	private int currentBtnId;

	public void addButton(ImageTextButton btn) {
		btnMap.put(btn.getId(), btn);
	}

	public void addViewButton(int viewId, ImageTextButton btn) {
		viewBtnMap.put(viewId, btn);
	}

	public void removeBtn(int id) {
		btnMap.remove(id);
	}

	public int size() {
		return btnMap.size();
	}

	public void selected(int id) {
		Set<Map.Entry<Integer, ImageTextButton>> entrySet = btnMap.entrySet();
		for (Map.Entry<Integer, ImageTextButton> entry : entrySet) {
			ImageTextButton btn = entry.getValue();
			if (id == entry.getKey()) {//选中
				btn.selected();
				currentBtnId = id;
			} else {
				btn.unselected();
			}
		}
	}

	/**
	 * 页面选中
	 *
	 * @param viewId 页面id
	 */
	public void viewSelected(int viewId) {
		ImageTextButton btn = viewBtnMap.get(viewId);
		selected(btn.getId());
	}

	/**
	 * 是否展示当前页之前的页面
	 *
	 * @param id 待展示的页面id
	 */
	public boolean isShowFrontPage(int id) {
		return btnMap.get(id).getSort() < btnMap.get(currentBtnId).getSort();
	}

}

