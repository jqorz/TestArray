package com.jqorz.common.floatView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.jqorz.common.R;

/**
 * copyright datedu
 *
 * @author j1997
 * @since 2019/9/29
 */
public class ControlFloatView extends View implements View.OnClickListener {

    private View tv_switcher;

    public ControlFloatView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_float_view, null);
        init();
    }

    private void init() {
        tv_switcher = findViewById(R.id.tv_switcher);
    }

    @Override
    public void onClick(View view) {
    }
}
