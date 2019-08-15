package com.jqorz.test.gson;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.test.base.BaseActivity;
import com.jqorz.test.util.GsonUtil;

import java.util.Map;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/8/8
 * 测试Gosn2Map方法的泛型参数
 * 如果使用T,在编译版本28，手机版本7.0的时候会崩溃，其他版本正常
 * 如果使用Object，都正常
 */
public class GsonTestActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, GsonTestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void init() {

    }

    public void onClickBtn1(View view) {
        //用T
        String responseModel = "{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": \"{\\\"author\\\":\\\"\\\",\\\"c1\\\":\\\"\\\",\\\"id\\\":15,\\\"sentence\\\":\\\"机会只对进取有为的人开放，庸人永远无法光顾。\\\"}\",\n" +
                "    \"msg\": \"操作成功\",\n" +
                "    \"responsetime\": 1565836727000\n" +
                "}";
        Map<String, Object> responsedata = GsonUtil.json2Map2(responseModel);
        Number code = (Number) responsedata.get("code");
        if (code.intValue() == 1 && responsedata.get("data") != null) {
            Map<String, Object> data = GsonUtil.json2Map(responsedata.get("data").toString());
            String sentence = data.get("sentence").toString();
            if (sentence != null) {
                Log.i("11", sentence);
            }
        }


    }

    public void onClickBtn2(View view) {
        //用Object
        String responseModel = "{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": \"{\\\"author\\\":\\\"\\\",\\\"c1\\\":\\\"\\\",\\\"id\\\":15,\\\"sentence\\\":\\\"机会只对进取有为的人开放，庸人永远无法光顾。\\\"}\",\n" +
                "    \"msg\": \"操作成功\",\n" +
                "    \"responsetime\": 1565836727000\n" +
                "}";
        Map<String, Object> responsedata = GsonUtil.json2Map(responseModel);
        Number code = (Number) responsedata.get("code");
        if (code.intValue() == 1 && responsedata.get("data") != null) {
            Map<String, Object> data = GsonUtil.json2Map(responsedata.get("data").toString());
            String sentence = data.get("sentence").toString();
            if (sentence != null) {
                Log.i("22", sentence);
            }
        }
    }

    public void onClickBtn3(View view){
        //字符串2 用T
        String s = "{\"code\":1,\"data\":\"{\\\"author\\\":\\\"\\\",\\\"c1\\\":\\\"\\\",\\\"id\\\":8,\\\"sentence\\\":\\\"自信是成功的第一秘诀。——爱默生\\\"}\",\"msg\":\"操作成功\",\"responsetime\":1565238517000}";
        Map<String, Object> map = GsonUtil.json2Map2(s);
        if (map != null) {
            System.out.println("3333 " + map.get("code"));
        }
    }
    public void onClickBtn4(View view){
        //字符串2 用Object
        String s = "{\"code\":1,\"data\":\"{\\\"author\\\":\\\"\\\",\\\"c1\\\":\\\"\\\",\\\"id\\\":8,\\\"sentence\\\":\\\"自信是成功的第一秘诀。——爱默生\\\"}\",\"msg\":\"操作成功\",\"responsetime\":1565238517000}";
        Map<String, Object> map = GsonUtil.json2Map(s);
        if (map != null) {
            System.out.println("444 " + map.get("code"));
        }
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_gson;
    }

    private class Man {
        private String name;
        private String age;

        public Man(String name, String age) {
            this.name = name;
            this.age = age;
        }
    }
}
