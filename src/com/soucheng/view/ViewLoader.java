package com.soucheng.view;

import android.content.Context;
import android.view.View;

/**
 * View加载类
 *
 * @author lichen
 */
public abstract class ViewLoader {

	protected Context context;
	protected View view;

	protected ViewLoader() {
	}

	protected ViewLoader(Context context, View view) {
		this.context = context;
		this.view = view;
	}

	public abstract void load();
}
