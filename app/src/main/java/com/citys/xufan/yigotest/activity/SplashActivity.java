package com.citys.xufan.yigotest.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.citys.xufan.yigotest.MainActivity;
import com.citys.xufan.yigotest.PermissionParams;
import com.citys.xufan.yigotest.R;
import com.citys.xufan.yigotest.utils.SpUtil;

import java.util.ArrayList;

public class SplashActivity extends BaseActivity {
    private ImageView imgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        animateImg();
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }
    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void afterInitView() {

    }

    @Override
    protected void initView() {
        imgSplash = findViewById(R.id.img_splash);
    }

    @Override
    protected void beforeLayout() {

    }

    private void main(){
        if(checkPermissions(PermissionParams.PERMISSIONS)) {
            isLogin();
        } else {
            requestPermissions(PermissionParams.PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==REQUEST_PERMISSION_CODE){
            if (grantResults.length>0){
                denyPermissionList =new ArrayList<>();
                for (int i = 0; i <grantResults.length ; i++) {
                    if (grantResults[i]!= PackageManager.PERMISSION_GRANTED){
                        denyPermissionList.add(permissions[i]);
                    }
                }
            }
            isLogin();
        }

    }

    private void isLogin(){
        final boolean isGuide = SpUtil.getInstance().getBoolean("isGuider");
        final boolean islogin = SpUtil.getInstance().getloginstatue("islogin");
        if (isGuide && islogin) {
            Log.d("TAG", "分支1");
            Intent main = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(main);
            SplashActivity.this.finish();
        } else if (isGuide && !islogin) {
            Log.d("TAG", "分支2");
            Intent main1 = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(main1);
            SplashActivity.this.finish();
        } else {
            Log.d("TAG", "分支3");
            Intent guide = new Intent(SplashActivity.this, GuideActivity.class);
            startActivity(guide);
            SplashActivity.this.finish();
        }
    }

    private void animateImg() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                main();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imgSplash.startAnimation(anim);
//        mHandler.sendEmptyMessageDelayed(101,2000);
    }
    @Override
    public void onClick(View view) {

    }
}