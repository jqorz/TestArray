package com.jqorz.test.floatView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.jqorz.test.R;

/**
 * copyright datedu
 *
 * @author j1997
 * @since 2019/9/29
 */
public class ControlFloatView extends LinearLayout implements View.OnClickListener {


    public ControlFloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_float_view, this);
    }


    @Override
    public void onClick(View view) {
    }
}
