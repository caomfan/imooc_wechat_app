package com.mophan.imooc_wechat_app;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mophan.imooc_wechat_app.fragment.SplashFragment;
import com.mophan.imooc_wechat_app.fragment.TabFragment;
import com.mophan.imooc_wechat_app.view.TabView;
import com.mophan.imooc_wechat_app.view.transformer.ScaleTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private ViewPager mVpMain;
    private int[] mResIds = new int[]{
            R.drawable.guide_image1,
            R.drawable.guide_image2,
            R.drawable.guide_image3,
            R.drawable.guide_image4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mVpMain = findViewById(R.id.vp_main);
        mVpMain.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return SplashFragment.newInstance(mResIds[position]);
            }

            @Override
            public int getCount() {
                return mResIds.length;
            }
        });
        mVpMain.setPageTransformer(true, new ScaleTransformer());
    }
}
