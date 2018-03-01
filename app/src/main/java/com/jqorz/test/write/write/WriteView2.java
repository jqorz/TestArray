package com.jqorz.test.write.write;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;


/**
 * 使用三个缓存点
 * 粗细可变
 */
public class WriteView2 extends View {
    private static final float TOUCH_TOLERANCE = 4;
    // 画笔，定义绘制属性
    private Paint mPaint;
    // 绘制路径
    private Path mPath;
    // 画布及其底层位图
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private float paintWidthPrevious;

    private PointF point_start, point_middle, point_end;

    private VelocityTracker mVelocityTracker;

    public WriteView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }


    /**
     * 初始化工作
     */
    private void initialize() {

        // 绘制自由曲线用的画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);//设置圆弧连接
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置圆线帽
        mPaint.setStrokeWidth(12);
        mPaint.setPathEffect(new CornerPathEffect(5));

        mPath = new Path();

        point_start = new PointF(0, 0);
        point_middle = new PointF(0, 0);
        point_end = new PointF(0, 0);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);//绘制Bitamp的画布对象

        mBitmap.eraseColor(Color.WHITE);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
//                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.computeCurrentVelocity(10);
                float speed = (getSpeed(mVelocityTracker.getXVelocity(),
                        mVelocityTracker.getYVelocity()) / 2f);
                paintWidthPrevious = getPaintWidth(speed);
                mPaint.setStrokeWidth(paintWidthPrevious);
                touch_move(x, y);
                break;
            case MotionEvent.ACTION_UP:
                touch_up(x, y);
                invalidate();
                break;
        }
        return true;
    }

    private float getPaintWidth(float speed) {
        Log.i("jq", "speed = " + speed);
        float max_width = 25f;
        float min_width = 5f;
        float max_dif = 0.5f;
        float max_speed = 45f;
        float min_speed = 0.5f;

        float width;

        speed = Math.max(min_speed, speed);
        speed = Math.min(max_speed, speed);

        //将 speed min_speed-max_speed 映射到 width min_width-max_width
        width = (speed - min_speed) / (max_speed - min_speed) * (max_width - min_width) + min_width;

        Log.i("jq", "width = " + width);

        width = Math.max(min_width, width);
        width = Math.min(max_width, width);

        //宽度渐变
        if (Math.abs(width - paintWidthPrevious) > max_dif) {
            if (width > paintWidthPrevious)
                width = paintWidthPrevious + max_dif;
            else
                width = paintWidthPrevious - max_dif;
        }
        // printf("d:%.4f, time_diff:%lld, speed:%.4f, width:%.4f\n", d, e.t-b.t, s, w);
        return width;
    }

    private float getSpeed(float xVelocity, float yVelocity) {
        return (float) Math.sqrt(Math.pow(xVelocity, 2) + (Math.pow(yVelocity, 2)));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //将路径绘制在自己的Bitmap上

        mCanvas.drawPath(mPath, mPaint);
        //将Bitmap绘制到界面上
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        mPath.reset();

        // 直接绘制路径
//        canvas.drawPath(mPath, mPaint);
    }


    private void touch_start(float x, float y) {
//        mX = x;
//        mY = y;

        mPath.moveTo(x, y);

        point_start.x = (int) x;
        point_start.y = (int) y;

        point_middle.x = (int) x;
        point_middle.y = (int) y;

        point_end.x = (int) x;
        point_end.y = (int) y;

    }

    private void touch_move(float x, float y) {
        if (getUsefulPoint(x, y, point_end.x, point_end.y)) {
            mPath.moveTo(point_start.x, point_start.y);
            mPath.quadTo(point_start.x, point_start.y, (point_middle.x + point_start.x) / 2, (point_middle.y + point_start.y) / 2);
            mPath.quadTo(point_middle.x, point_middle.y, (point_middle.x + point_end.x) / 2, (point_middle.y + point_end.y) / 2);
            mPath.quadTo((point_middle.x + point_end.x) / 2, (point_middle.y + point_end.y) / 2, point_end.x, point_end.y);
            //  mPath.lineTo(point_end.x, point_end.y);
            point_start.x = point_middle.x;
            point_start.y = point_middle.y;

            point_middle.x = point_end.x;
            point_middle.y = point_end.y;

            point_end.x = (int) x;
            point_end.y = (int) y;
            invalidate();

        }

    }

    private void touch_up(float x, float y) {
//        mPath.lineTo(point_end.x, point_end.y);
    }

    /**
     * 过滤掉距离过小的点
     */
    private boolean getUsefulPoint(float x1, float y1, float x2, float y2) {
        return (Math.abs(x1 - x2) >= TOUCH_TOLERANCE || Math.abs(y1 - y2) >= TOUCH_TOLERANCE);
    }

    /**
     * 清除整个图像
     */
    public void clear() {
        // 清除方法1：重新生成位图
        // mBitmap = Bitmap
        // .createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        // mCanvas = new Canvas(mBitmap);
        // 清除方法2：将位图清除为白色
        if (mBitmap != null)
            mBitmap.eraseColor(Color.WHITE);
        // 两种清除方法都必须加上后面这两步：
        // 路径重置
        mPath.reset();
        // 刷新绘制
        invalidate();
    }

}
