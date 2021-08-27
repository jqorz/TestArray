package com.jqorz.test.view.multi_touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;

/**
 * 多点触控并显示触摸点位置
 * @author jqorz
 * @since 2018/1/15.
 */

public class LightView extends View {

    SparseArray<FluorescencePointF> mActivePointers = new SparseArray<>();

    public LightView(Context context) {
        super(context);
    }

    public LightView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //当前DOWN或者UP的是手指的index
        int curPointerIndex = event.getActionIndex();
        //通过index获得当前手指的id
        int curPointerId = event.getPointerId(curPointerIndex);

        int actionMasked = event.getActionMasked();

        Log.i("getPressure", "curPointerIndex = " + curPointerIndex + " value = " + event.getPressure(curPointerIndex));
        Log.i("getSize", "curPointerIndex = " + curPointerIndex + " value = " + event.getSize(curPointerIndex));


        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                PointF pointF = new PointF();
                pointF.x = event.getX(curPointerIndex);
                pointF.y = event.getY(curPointerIndex);
                FluorescencePointF fluorescencePointF = new FluorescencePointF();
                fluorescencePointF.setPointF(pointF.x, pointF.y);
                mActivePointers.append(curPointerId, fluorescencePointF);
                break;

            case MotionEvent.ACTION_MOVE:
                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    FluorescencePointF fluorescencePointF1 = mActivePointers.get(event.getPointerId(i));
                    if (fluorescencePointF1 != null) {
                        fluorescencePointF1.setPointF(event.getX(i), event.getY(i));
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mActivePointers.remove(curPointerId);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {


        for (int size = mActivePointers.size(), i = 0; i < size; i++) {
            FluorescencePointF f = mActivePointers.valueAt(i);
            canvas.drawCircle(f.getmPointF().x, f.getmPointF().y, f.getRadius(), f.getmPaint());

        }
    }
}
