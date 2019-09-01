package com.mophan.imooc_wechat_app.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.mophan.imooc_wechat_app.MainActivity;
import com.mophan.imooc_wechat_app.R;
import com.mophan.imooc_wechat_app.utils.L;

/**
 * Create by CMF on 2019/9/1.
 */
public class TabFragment extends Fragment {

    private static final String BUNDLE_KEY_TITLE="key_title";
    private TextView mTvTitle;
    private String mTitle;

    public static interface OnTitleClickListener{
        void onClick(String title);
    }

    private OnTitleClickListener mListener;
    public void setOnTitleClickListenr(OnTitleClickListener listenr){
        mListener=listenr;
    }

    public static TabFragment newInstance(String title){
        Bundle bundle=new Bundle();
        bundle.putString(BUNDLE_KEY_TITLE,title);

        TabFragment tabFragment=new TabFragment();
        tabFragment.setArguments(bundle);
        return  tabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(arguments!=null)
        {
            mTitle=arguments.getString(BUNDLE_KEY_TITLE,"");
        }
        L.d("onCreate , Title = "+mTitle);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        L.d("onCreateView , Title = "+mTitle);
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvTitle = view.findViewById(R.id.tv_title);
        mTvTitle.setText(mTitle);

        mTvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取activity对象
                if(mListener!=null){
                    mListener.onClick("微信changed！");
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.d("onDestroyView , Title = "+mTitle);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.d("onDestroy , Title = "+mTitle);
    }

    public void changeTitle(String title){
        if(!isAdded()){
            return;
        }
        mTvTitle.setText(title);
    }
}
