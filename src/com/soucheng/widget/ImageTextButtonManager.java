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

    private Context context;

    private Map<Integer, ImageTextButton> btnMap = new HashMap<>();

    public ImageTextButtonManager(Context context){
        this.context = context;
    }

    public void addButton(ImageTextButton btn) {
        btnMap.put(btn.getId(), btn);
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
                btn.setTextColor(context.getResources().getColor(R.color.green));
                btn.setBackground(context.getResources().getDrawable(R.drawable.image_btn_selected));
            } else {
                btn.setTextColor(context.getResources().getColor(R.color.dark_gray));
                btn.setBackground(context.getResources().getDrawable(R.drawable.image_btn_normal));
            }
        }
    }

}

