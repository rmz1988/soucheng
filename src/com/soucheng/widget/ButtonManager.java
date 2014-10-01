package com.soucheng.widget;

import android.content.Context;
import android.util.TypedValue;
import android.widget.Button;
import com.soucheng.activity.R;

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
                btn.setBackground(context.getResources().getDrawable(R.drawable.nav_selected));
                btn.setTextColor(context.getResources().getColor(R.color.orange));
                btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            } else {
                btn.setBackground(context.getResources().getDrawable(R.drawable.nav_normal));
                btn.setTextColor(context.getResources().getColor(R.color.white));
                btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
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

