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
}
