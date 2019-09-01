package com.mophan.imooc_wechat_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mophan.imooc_wechat_app.fragment.TabFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVpMain;
    private List<String> mTitles = new ArrayList<>(Arrays.asList("微信", "通讯录", "发现", "我"));

    private Button mBtnWeChat;
    private Button mBtnFriend;
    private Button mBtnFind;
    private Button mBtnMine;

    private List<Button> mTabs = new ArrayList<>();

    private SparseArray<TabFragment> mFragements = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVpMain = findViewById(R.id.vp_main);

        initViews();
        initViewPagerAdapter();
    }

    private void initViewPagerAdapter() {
        mVpMain.setOffscreenPageLimit(mTitles.size());
        mVpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                TabFragment fragment = TabFragment.newInstance(mTitles.get(position));
                if (position == 0) {
                    fragment.setOnTitleClickListenr(new TabFragment.OnTitleClickListener() {
                        @Override
                        public void onClick(String title) {
                            changeWeChatTab(title);
                        }
                    });
                }
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
                    Button left = mTabs.get(position);
                    Button right = mTabs.get(position + 1);
                    left.setText((1 - positionOffset) + "");
                    right.setText(positionOffset + "");
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
        mBtnWeChat = findViewById(R.id.btn_wechat);
        mBtnFriend = findViewById(R.id.btn_friend);
        mBtnFind = findViewById(R.id.btn_find);
        mBtnMine = findViewById(R.id.btn_mine);

        mTabs.add(mBtnWeChat);
        mTabs.add(mBtnFriend);
        mTabs.add(mBtnFind);
        mTabs.add(mBtnMine);

        mBtnWeChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取第一个Fragement
                TabFragment tabFragment = mFragements.get(0);
                if (tabFragment != null) {
                    tabFragment.changeTitle("微信 changed！");
                }
            }
        });
    }

    public void changeWeChatTab(String title) {
        mBtnWeChat.setText(title);
    }
}
