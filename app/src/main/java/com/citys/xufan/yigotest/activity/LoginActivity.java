package com.citys.xufan.yigotest.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.citys.xufan.yigotest.MainActivity;
import com.citys.xufan.yigotest.R;
import com.citys.xufan.yigotest.bean.LoginBean;
import com.citys.xufan.yigotest.http.GsonUtil;
import com.citys.xufan.yigotest.http.OkHttpMgr;
import com.citys.xufan.yigotest.http.OkMsgCallback;
import com.citys.xufan.yigotest.utils.SpUtil;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity {

    private static String TAG = "LoginActivity";

    private ShapeableImageView imgHead;
    private AppCompatEditText edUser, edPwd;
    private Button btnLogin;

    @Override
    protected void onStop() {
        super.onStop();
        LoginActivity.this.finish();

    }

    @Override
    protected int contentLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void afterInitView() {

    }
    private void httpLogin(){
        String inputName = edUser.getText().toString();
        String inputPassword = edPwd.getText().toString();
        Map<String,String> map = new HashMap<>();
        map.put("name",inputName);
        map.put("password",inputPassword);
        String urlTag = "user/loginAccount";
        OkHttpMgr.getInstance().postJson(urlTag, map, new OkMsgCallback() {
            @Override
            public void fail(String error) {
                Log.e(TAG, "error:" + error);
            }

            @Override
            public void success(int code, String body) {
                if(code == 200) {
                    LoginBean bean = GsonUtil.inst().getTypeJson(body, LoginBean.class);
                    startActivity(new Intent(LoginActivity.this.getBaseContext(), MainActivity.class));
                    SpUtil.getInstance().save("isLogin", true);
                    //持久化保存登录数据
                    if(bean.getData() != null) {
                        LoginBean.UserBean data = bean.getData();
                        SpUtil.getInstance().save("userId", data.getUserId());
                        SpUtil.getInstance().save("name", data.getName());
                        SpUtil.getInstance().save("headImage", data.getHeadImage());
                        SpUtil.getInstance().save("phoneNum", data.getPhoneNum());
                        SpUtil.getInstance().save("balance", data.getBalance().floatValue());
                    }
                }
            }
        });

    }

    @Override
    protected void initView() {
        imgHead = findViewById(R.id.img_login_head);
        edUser = findViewById(R.id.ed_login_user);
        edPwd = findViewById(R.id.ed_login_pwd);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.hide();
        }
    }

    @Override
    protected void beforeLayout() {

    }

    @Override
    public void onClick(View v) {
        String inputName = edUser.getText().toString();
        String inputPassword = edPwd.getText().toString();
        if(v.getId() == R.id.btn_login) {
            if (inputName.equals("") && inputPassword.equals("")){
                Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
            } else if (inputName.equals("") || inputPassword.equals("")){
                Toast.makeText(LoginActivity.this, "请输入用户名或密码", Toast.LENGTH_SHORT).show();
            } else {
                httpLogin();
            }
        }

    }
}