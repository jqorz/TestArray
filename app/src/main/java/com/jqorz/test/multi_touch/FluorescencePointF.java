package com.jqorz.test.multi_touch;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Shader;

/**
 * @author jqorz
 * @since 2018/1/15.
 */

public class FluorescencePointF {
    private PointF mPointF;   //荧光点坐标
    private float mRadius = 80;    //荧光点半径
    private Paint mPaint = new Paint();

    public PointF getmPointF() {
        return mPointF;
    }

    public Paint getmPaint() {
        return mPaint;
    }


    public void setPointF(float x, float y) {
        this.mPointF = new PointF(x, y);
        RadialGradient shader = new RadialGradient(x, y, getRadius(), Color.BLUE, Color.WHITE,
                Shader.TileMode.CLAMP);//自定义环形渲染器
        mPaint.setShader(shader);
    }


    public float getRadius() {
        return mRadius;
    }


}