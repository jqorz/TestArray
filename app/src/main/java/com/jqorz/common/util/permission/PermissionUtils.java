package com.jqorz.common.util.permission;

import android.app.Activity;
import android.content.Context;

import com.jqorz.common.util.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;
import com.yanzhenjie.permission.runtime.PermissionDef;

import java.util.List;

/**
 * copyright: datedu
 * author:  br2ant3
 * date:  2018/3/15
 * description:
 */
public class PermissionUtils {

    /**
     * 使用框架申请权限
     * 使用帮助：https://github.com/yanzhenjie/AndPermission/blob/master/README-CN.md
     */


    public static void readyPermission(final Activity activity, final IPermissionListener listener, @PermissionDef String... permissions) {
        AndPermission.with(activity)
                .runtime()
                .permission(permissions)
                .onGranted(permissions12 -> {
                    if (listener != null) listener.onSucceed();
                })
                .onDenied(permissions1 -> {

                    if (AndPermission.hasAlwaysDeniedPermission(activity, permissions1)) {
                        ToastUtil.showToast("权限被拒绝");
                    } else {
//                            if (listener != null) listener.onFailure();
                    }
                })
                .start();
    }


    public static String getPermissionText(Context context, List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        StringBuilder sb = new StringBuilder();
        for (String permissionName : permissionNames) {
            sb.append(" ");
            sb.append(permissionName);
        }
        return sb.toString();
    }

    public interface IPermissionListener {
        void onSucceed();
    }

    public static class PermissionResult {
        public static final int SUCCESS = 0;
        public static final int QUERY = 1;
        public static final int FAILURE = 2;
        public int result;
        public String requestCode;

        public PermissionResult(String requestCode, int result) {
            this.requestCode = requestCode;
            this.result = result;
        }

        public PermissionResult(int result) {
            this.result = result;
        }
    }
}