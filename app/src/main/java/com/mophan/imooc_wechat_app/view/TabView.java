package com.mophan.imooc_wechat_app.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mophan.imooc_wechat_app.R;

/**
 * Create by CMF on 2019/9/1.
 */
public class TabView extends FrameLayout {

    private ImageView mIvIcon;
    private ImageView mIvIconSelect;
    private TextView mTvTitle;
    private ArgbEvaluator mArgbEva;

    private static final int COLOR_DEFAULT = Color.parseColor("#ff000000");
    private static final int COLOR_SELECT = Color.parseColor("#ff45c01a");

    public TabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.tab_view, this);
        mIvIcon = findViewById(R.id.iv_icon);
        mIvIconSelect = findViewById(R.id.iv_icon_select);
        mTvTitle = findViewById(R.id.tv_title);
        mArgbEva = new ArgbEvaluator();

        setProgress(0);
    }

    public void setIconAndText(int icon,int iconSelect,String text){
        mIvIcon.setImageResource(icon);
        mIvIconSelect.setImageResource(iconSelect);
        mTvTitle.setText(text);
    }

    public void setProgress(float progress) {
        mIvIcon.setAlpha(1 - progress);
        mIvIconSelect.setAlpha(progress);
        mTvTitle.setTextColor((Integer) mArgbEva.evaluate(progress, COLOR_DEFAULT, COLOR_SELECT));
    }
}
