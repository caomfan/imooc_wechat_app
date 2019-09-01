package com.mophan.imooc_wechat_app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mophan.imooc_wechat_app.fragment.SplashFragment;
import com.mophan.imooc_wechat_app.view.transformer.RotateTransfomer;
import com.mophan.imooc_wechat_app.view.transformer.ScaleTransformer;

public class BannerActivity extends AppCompatActivity {

    private ViewPager mVpMain;
    private int[] mResIds = new int[]{
            0xffff0000,
            0xff00ff00,
            0xff0000ff,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mVpMain = findViewById(R.id.vp_main);
        mVpMain.setOffscreenPageLimit(3);
        mVpMain.setPageMargin(40);
        mVpMain.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mResIds.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                //return super.instantiateItem(container, position);

                View view=new View(container.getContext());
                view.setBackgroundColor(mResIds[position]);
                ViewGroup.LayoutParams lp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                view.setLayoutParams(lp);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View)object);
            }
        });
        mVpMain.setPageTransformer(true,new RotateTransfomer());
    }
}
