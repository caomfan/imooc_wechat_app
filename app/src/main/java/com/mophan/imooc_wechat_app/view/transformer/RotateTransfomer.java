package com.mophan.imooc_wechat_app.view.transformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * Create by CMF on 2019/9/2.
 */
public class RotateTransfomer implements ViewPager.PageTransformer {

    private static final int MAX_ROTATE = 15;
    //a->b
    //a, position:(0,-1)
    //b,position:(1,0)

    //b->a
    //b, position:(0,1)
    //a,position:(-1,0)
    @Override
    public void transformPage(@NonNull View page, float position) {
        //[,-1]
        if (position < -1) {
            page.setRotation(-MAX_ROTATE);
            page.setPivotY(page.getHeight());
            page.setPivotX(page.getWidth());

            //[-1,1]
        } else if (position <= 1) {

            //左边的页面
            if (position < 0) {

                page.setPivotY(page.getHeight());
                page.setPivotX(page.getWidth() * 0.5f + 0.5f * -position * page.getWidth());

                page.setRotation(MAX_ROTATE * position);
            } else {//右边的页面
                page.setPivotY(page.getHeight());
                page.setPivotX(page.getWidth() * 0.5f*(1 - position) );

                page.setRotation(MAX_ROTATE * position);
            }
            //[1,]
        } else {
            page.setRotation(MAX_ROTATE);
            page.setPivotY(page.getHeight());
            page.setPivotX(0);
        }
    }
}
