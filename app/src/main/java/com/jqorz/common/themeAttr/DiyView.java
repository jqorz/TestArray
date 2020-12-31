package com.jqorz.common.themeAttr;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.jqorz.common.R;

/**
 * copyright datedu
 *
 * @author jqorz
 * @since 2020/1/20
 */
public class DiyView extends RelativeLayout {
    public DiyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_diy_test, this);
    }
}
