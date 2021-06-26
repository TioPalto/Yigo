package com.citys.xufan.yigotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.citys.xufan.yigotest.fragment.pageFragment1;
import com.citys.xufan.yigotest.fragment.pageFragment2;
import com.citys.xufan.yigotest.fragment.pageFragment3;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private LinearLayout mTab3;

    private ImageButton mImg1;
    private ImageButton mImg2;
    private ImageButton mImg3;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private Fragment mFrag1;
    private Fragment mFrag2;
    private Fragment mFrag3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();//初始化控件
        initEvents();//初始化事件
        selectTab(0);//默认选中第一个Tab
    }

    private void initEvents() {
        //初始化四个Tab的点击事件
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
        mTab3.setOnClickListener(this);
    }

    private void initViews() {
        //初始化四个Tab的布局文件
        mTab1 = (LinearLayout) findViewById(R.id.id_tab1);
        mTab2 = (LinearLayout) findViewById(R.id.id_tab2);
        mTab3 = (LinearLayout) findViewById(R.id.id_tab3);

        //初始化四个ImageButton
        mImg1 = (ImageButton) findViewById(R.id.id_tab_img1);
        mImg2 = (ImageButton) findViewById(R.id.id_tab_img2);
        mImg3 = (ImageButton) findViewById(R.id.id_tab_img3);


        textView1 = (TextView) findViewById(R.id.id_tab_text1);
        textView2 = (TextView) findViewById(R.id.id_tab_text2);
        textView3 = (TextView) findViewById(R.id.id_tab_text3);
    }

    private void selectTab(int i){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(transaction);
        switch (i){
            case 0:
                mImg1.setImageResource(R.mipmap.main_selected);
                textView1.setTextColor(getResources().getColor(R.color.tab_text_green));
                if (mFrag1 == null){
                    mFrag1 =new pageFragment1();
                    transaction.add(R.id.id_content,mFrag1);
                } else {
                    transaction.show(mFrag1);
                }
                break;
            case 1:
                mImg2.setImageResource(R.mipmap.subs_selected);
                textView2.setTextColor(getResources().getColor(R.color.tab_text_green));
                if (mFrag2 == null){
                    mFrag2 =new pageFragment2();
                    transaction.add(R.id.id_content,mFrag2);
                } else {
                    transaction.show(mFrag2);
                }
                break;
            case 2:
                mImg3.setImageResource(R.mipmap.mine_select);
                textView3.setTextColor(getResources().getColor(R.color.tab_text_green));
                if (mFrag3 == null) {
                    mFrag3 = new pageFragment3();
                    transaction.add(R.id.id_content, mFrag3);
                } else {
                    transaction.show(mFrag3);
                }
                break;

        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mFrag1 != null) {
            transaction.hide(mFrag1);
        }
        if (mFrag2 != null) {
            transaction.hide(mFrag2);
        }
        if (mFrag3 != null) {
            transaction.hide(mFrag3);
        }
    }

    private void resetImgs() {
        mImg1.setImageResource(R.mipmap.main_unselected);
        mImg2.setImageResource(R.mipmap.subs_unselected);
        mImg3.setImageResource(R.mipmap.mine_unselected);

        textView1.setTextColor(getResources().getColor(R.color.tab_text));
        textView2.setTextColor(getResources().getColor(R.color.tab_text));
        textView3.setTextColor(getResources().getColor(R.color.tab_text));
    }

    @Override
    public void onClick(View v) {
        resetImgs(); //先将四个ImageButton置为灰色
        switch (v.getId()) {
            case R.id.id_tab1:
                selectTab(0);
                break;
            case R.id.id_tab2:
                selectTab(1);
                break;
            case R.id.id_tab3:
                selectTab(2);
                break;
        }
    }
}