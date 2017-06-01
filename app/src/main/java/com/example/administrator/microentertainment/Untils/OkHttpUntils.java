package com.example.administrator.microentertainment.Untils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/1.
 */

public class OkHttpUntils {
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();

    final OkHttpClient okHttpClient1 = new OkHttpClient.Builder()
            .cookieJar(new CookieJar() {
                @Override
                public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                    cookieStore.put("Cookie", list);
                }
                @Override
                public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                    List<Cookie> cookies = cookieStore.get(httpUrl.host());
                    return cookies != null ? cookies : new ArrayList<Cookie>();
                }
            })
            .build();



    public void getCode(String pNum){
        final String url = "http://120.25.192.181/xiaolv/admin/api/send_message.php?phone="+pNum;

        new Thread(new Runnable() {
            @Override
            public void run() {

                Request request = new Request.Builder()
                        .url(url)
                        .build();
                Call call = okHttpClient1.newCall(request);
                try {
                    Response response = call.execute();
                    System.out.println("body111111------------:"+response.body().string());
                    System.out.println("cookiestore11111111-----------:"+cookieStore.get("Cookie").get(0));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();

//        OkHttpClient okHttpClient = new OkHttpClient();

    }

    public void macthCode(String Code){
        final String url = "http://120.25.192.181/xiaolv/admin/api/validate_message1.php?code="+Code;

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(url)
                        .addHeader("Cookie", String.valueOf(cookieStore.get("Cookie").get(0)))
                        .build();
                Call call = okHttpClient1.newCall(request);
                try {
                    Response response = call.execute();
                    System.out.println("body222222------------:"+response.body().string());
                    System.out.println("cookiestore2222222-----------:"+cookieStore.get("Cookie").get(0));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();

//        OkHttpClient okHttpClient = new OkHttpClient();

    }

}
