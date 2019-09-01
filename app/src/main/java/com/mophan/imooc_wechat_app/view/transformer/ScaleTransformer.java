package com.mophan.imooc_wechat_app.view.transformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.mophan.imooc_wechat_app.utils.L;

/**
 * Create by CMF on 2019/9/1.
 */
public class ScaleTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        L.d(page.getTag() + " , pos = " + position);
        //a->b
        //a, position:(0,-1)
        //b,position:(1,0)

        //b->a
        //b, position:(0,1)
        //a,position:(-1,0)

        //[,-1]
        if (position < -1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);

            //[-1,1]
        } else if (position <= 1) {

            //左边的页面
            if (position < 0) {
                //a->b position:(0,-1)
                //[1,0.75f]
                float scaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
                page.setScaleX(scaleA);
                page.setScaleY(scaleA);

                float alphaA=MIN_ALPHA+(1-MIN_ALPHA)*(1+position);
                page.setAlpha(alphaA);

                //b->a position:(-1,0)
                //[0.75f,1]

            } else {//右边的页面
                float scaleB = MIN_SCALE + (1 - MIN_SCALE) * (1 -position);
                page.setScaleX(scaleB);
                page.setScaleY(scaleB);

                float alphaB=MIN_ALPHA+(1-MIN_ALPHA)*(1-position);
                page.setAlpha(alphaB);
            }
            //[1,]
        } else {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
        }
    }
}
