package com.soucheng.vo;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * @author lichen
 */
public class MenuIcon implements Serializable {

    private static final long serialVersionUID = -7900712280991877916L;

    private Drawable normal;
    private Drawable select;

    public MenuIcon(Drawable normal, Drawable select) {
        this.normal = normal;
        this.select = select;
    }

    public Drawable getNormal() {
        return normal;
    }

    public void setNormal(Drawable normal) {
        this.normal = normal;
    }

    public Drawable getSelect() {
        return select;
    }

    public void setSelect(Drawable select) {
        this.select = select;
    }
}
