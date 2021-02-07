package com.jqorz.test.gson;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jqorz.test.R;
import com.jqorz.common.base.BaseActivity;
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


    public void onClickBtn1(View view) {
        //字符串2 用T
        String s = "{\"code\":1,\"data\":\"{\\\"author\\\":\\\"\\\",\\\"c1\\\":\\\"\\\",\\\"id\\\":8,\\\"sentence\\\":\\\"自信是成功的第一秘诀。——爱默生\\\"}\",\"msg\":\"操作成功\",\"responsetime\":1565238517000}";
        Map<String, Object> map = GsonUtil.json2Map(s);
        if (map != null) {
            System.out.println("jqjq 111" + map.get("code"));
        }
    }

    public void onClickBtn2(View view) {
        //字符串2 用Object
        String s = "{\"code\":1,\"data\":\"{\\\"author\\\":\\\"\\\",\\\"c1\\\":\\\"\\\",\\\"id\\\":8,\\\"sentence\\\":\\\"自信是成功的第一秘诀。——爱默生\\\"}\",\"msg\":\"操作成功\",\"responsetime\":1565238517000}";
        Map<String, Object> map = GsonUtil.json2Map2(s);
        if (map != null) {
            System.out.println("jqjq 222" + map.get("code"));
        }
    }

    @Override
    protected void init() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_gson;
    }

}
