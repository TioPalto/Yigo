package com.citys.xufan.yigotest.http;

public interface OkMsgCallback {

    void fail(String error);
    void success(int code, String body);

}
