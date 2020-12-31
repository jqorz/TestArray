package com.jqorz.test.util.permission;

import com.yanzhenjie.permission.Action;

import java.util.List;

/**
 * <pre>
 *     copyright: datedu
 *     author : br2ant3
 *     e-mail : xxx@xx
 *     time   : 2019/07/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GrantedAction implements Action<List<String>> {

    private PermissionUtils.IPermissionListener mListener;

    public GrantedAction(PermissionUtils.IPermissionListener listener) {
        mListener = listener;
    }

    @Override
    public void onAction(List<String> data) {
        if (mListener != null) mListener.onSucceed();
    }
}
