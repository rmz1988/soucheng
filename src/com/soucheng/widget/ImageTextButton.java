package com.soucheng.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.soucheng.activity.R;

/**
 * 自定义图片文字按钮
 *
 * @author lichen
 */
public class ImageTextButton extends LinearLayout {

	private int imageSrc;
	private int text;
	private float textSize;
	private int textColor;

	private int sort;

	private int selectedImageSource;
	private int normalImageSource;
	private int selectedColor;
	private int normalColor;
	private int selectedBackground;
	private int normalBackground;

	private ImageView imageView;
	private TextView textView;

	public ImageTextButton(Context context) {
		this(context, null);

	}

	public ImageTextButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ImageTextButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context).inflate(R.layout.image_text_button, this, true);
		imageView = (ImageView) findViewById(R.id.btn_img);
		textView = (TextView) findViewById(R.id.btn_text);
		setClickable(true);
		setFocusable(true);
	}

	public void selected() {
		imageView.setImageResource(selectedImageSource);
		textView.setTextColor(selectedColor);
		setBackgroundResource(selectedBackground);
	}

	public void unselected() {
		imageView.setImageResource(normalImageSource);
		textView.setTextColor(normalColor);
		setBackgroundResource(normalBackground);
	}

	public int getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(int imageSrc) {
		this.imageSrc = imageSrc;
		imageView.setImageResource(imageSrc);
	}

	public int getText() {
		return text;
	}

	public void setText(int text) {
		this.text = text;
		textView.setText(text);
	}

	public float getTextSize() {
		return textSize;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
		textView.setTextSize(textSize);
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
		textView.setTextColor(textColor);
	}

	public int getSelectedImageSource() {
		return selectedImageSource;
	}

	public void setSelectedImageSource(int selectedImageSource) {
		this.selectedImageSource = selectedImageSource;
	}

	public int getNormalImageSource() {
		return normalImageSource;
	}

	public void setNormalImageSource(int normalImageSource) {
		this.normalImageSource = normalImageSource;
	}

	public int getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(int selectedColor) {
		this.selectedColor = selectedColor;
	}

	public int getNormalColor() {
		return normalColor;
	}

	public void setNormalColor(int normalColor) {
		this.normalColor = normalColor;
	}

	public int getSelectedBackground() {
		return selectedBackground;
	}

	public void setSelectedBackground(int selectedBackground) {
		this.selectedBackground = selectedBackground;
	}

	public int getNormalBackground() {
		return normalBackground;
	}

	public void setNormalBackground(int normalBackground) {
		this.normalBackground = normalBackground;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
}
