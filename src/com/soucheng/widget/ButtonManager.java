package com.soucheng.widget;

import android.content.Context;
import android.util.TypedValue;
import android.widget.Button;
import com.soucheng.activity.R;
import com.soucheng.vo.MenuIcon;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 导航按钮管理器
 *
 * @author lichen
 */
public class ButtonManager {

	private Map<Integer, Button> btnMap = new HashMap<>();
	private Map<Integer, Button> viewBtnMap = new HashMap<>();
	private Map<Integer, MenuIcon> menuIconMap = new HashMap<>();

	private Context context;

	public ButtonManager(Context context) {
		this.context = context;
	}

	public void addButton(Button btn) {
		btnMap.put(btn.getId(), btn);
	}

	public void addViewButton(int viewId, Button btn) {
		viewBtnMap.put(viewId, btn);
	}

	public void addButtonImg(int btnId, MenuIcon menuIcon) {
		menuIconMap.put(btnId, menuIcon);
	}

	public void removeBtn(int id) {
		btnMap.remove(id);
	}

	public int size() {
		return btnMap.size();
	}

	public void selected(int id) {
		Set<Map.Entry<Integer, Button>> entrySet = btnMap.entrySet();
		for (Map.Entry<Integer, Button> entry : entrySet) {
			Button btn = entry.getValue();
			if (id == entry.getKey()) {//选中
				//                btn.setBackground(context.getResources().getDrawable(R.drawable.nav_selected));
				btn.setCompoundDrawablesWithIntrinsicBounds(null, menuIconMap.get(id).getSelect(), null, null);
				btn.setTextColor(context.getResources().getColor(R.color.orange));
			} else {
				btn.setCompoundDrawablesWithIntrinsicBounds(null, menuIconMap.get(entry.getKey()).getNormal(), null,
						null);
				//                btn.setBackground(context.getResources().getDrawable(R.drawable.nav_normal));
				btn.setTextColor(context.getResources().getColor(R.color.gray_text));
			}
		}
	}

	/**
	 * 页面选中
	 *
	 * @param viewId 页面id
	 */
	public void viewSelected(int viewId) {
		Button btn = viewBtnMap.get(viewId);
		selected(btn.getId());
	}

}

