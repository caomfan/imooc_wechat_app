package com.mophan.imooc_wechat_app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mophan.imooc_wechat_app.fragment.TabFragment;
import com.mophan.imooc_wechat_app.view.TabView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivityWithTab extends AppCompatActivity {

    private ViewPager mVpMain;
    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));

    private TabView mBtnWeChat;
    private TabView mBtnFriend;
    private TabView mBtnFind;
    private TabView mBtnMine;

    private List<TabView> mTabs = new ArrayList<>();
    private SparseArray<TabFragment> mFragements = new SparseArray<>();
    private static final String BUNDLE_KEY_POS = "bundle_key_pos";

    private int mCurrentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);
        if (savedInstanceState != null) {
            mCurrentPos = savedInstanceState.getInt(BUNDLE_KEY_POS,0);
        }
        initViews();
        initViewPagerAdapter();
        initEvents();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_KEY_POS, mVpMain.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private void initEvents() {
        for (int i = 0; i < mTabs.size(); i++) {
            final TabView tabView = mTabs.get(i);
            final int finalI = i;
            tabView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mVpMain.setCurrentItem(finalI, false);
                    setCurrentTab(finalI);
                }
            });
        }
    }

    private void initViewPagerAdapter() {
        mVpMain = findViewById(R.id.vp_main);

        mVpMain.setOffscreenPageLimit(mTitles.size());
        mVpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                TabFragment fragment = TabFragment.newInstance(mTitles.get(position));
                return fragment;
            }

            @Override
            public int getCount() {
                return mTitles.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TabFragment tabFragment = (TabFragment) super.instantiateItem(container, position);
                mFragements.put(position, tabFragment);
                return tabFragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                mFragements.remove(position);
                super.destroyItem(container, position, object);
            }
        });

        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset > 0) {
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position + 1);
                    left.setProgress((1 - positionOffset));
                    right.setProgress(positionOffset);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViews() {
        mBtnWeChat = findViewById(R.id.tab_wechat);
        mBtnFriend = findViewById(R.id.tab_friend);
        mBtnFind = findViewById(R.id.tab_find);
        mBtnMine = findViewById(R.id.tab_mine);

        mBtnWeChat.setIconAndText(R.drawable.wechat, R.drawable.wechat_select, "微信");
        mBtnFriend.setIconAndText(R.drawable.friend, R.drawable.friend_select, "通讯录");
        mBtnFind.setIconAndText(R.drawable.find, R.drawable.find_select, "发现");
        mBtnMine.setIconAndText(R.drawable.mine, R.drawable.mine_select, "我");

        mTabs.add(mBtnWeChat);
        mTabs.add(mBtnFriend);
        mTabs.add(mBtnFind);
        mTabs.add(mBtnMine);

        setCurrentTab(mCurrentPos);
    }

    private void setCurrentTab(int pos) {
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            if (i == pos) {
                tabView.setProgress(1);
            } else {
                tabView.setProgress(0);
            }
        }
    }

}
