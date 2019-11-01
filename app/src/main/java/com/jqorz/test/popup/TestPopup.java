package com.jqorz.test.popup;

import android.content.Context;
import android.view.View;

import com.jqorz.test.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2019/11/1
 */
public class TestPopup extends BasePopupWindow {

    public TestPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.layout_popup);
    }
}
