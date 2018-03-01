package com.jqorz.test.write.write;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 基本贝塞尔曲线实现原画笔迹
 * 粗细固定
 */
public class WriteView1 extends View {
    private static final float TOUCH_TOLERANCE = 4;
    // 画笔，定义绘制属性
    private Paint myPaint;
    // 绘制路径
    private Path myPath;
    // 画布及其底层位图
    private Bitmap myBitmap;
    private Canvas myCanvas;
    private float mX, mY;


    public WriteView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }


    /**
     * 初始化工作
     */
    private void initialize() {

        // 绘制自由曲线用的画笔
        myPaint = new Paint();
        myPaint.setAntiAlias(true);
        myPaint.setDither(true);
        myPaint.setColor(Color.BLACK);
        myPaint.setStyle(Paint.Style.STROKE);
        myPaint.setStrokeJoin(Paint.Join.ROUND);//设置圆弧连接
        myPaint.setStrokeCap(Paint.Cap.ROUND);//设置圆线帽
        myPaint.setStrokeWidth(12);
        myPaint.setPathEffect(new CornerPathEffect(5));

        myPath = new Path();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        myBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(myBitmap);//绘制Bitamp的画布对象
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //如果不现在自己的Bitmap上绘制一层颜色，会出现锯齿
        myCanvas.drawColor(Color.WHITE);
        //将路径绘制在自己的Bitmap上
        myCanvas.drawPath(myPath, myPaint);
        //将Bitmap绘制到界面上
        canvas.drawBitmap(myBitmap, 0, 0, myPaint);

        // 直接绘制路径
//        canvas.drawPath(myPath, myPaint);
    }


    private void touch_start(float x, float y) {
        myPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touch_move(float x, float y) {
        if (getUsefulPoint(x, y, mX, mY)) {
            myPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
//            myPath.quadTo((x + 2 * mX) / 3f, (y + 2 * mY) / 3f, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        myPath.lineTo(mX, mY);
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
        // myBitmap = Bitmap
        // .createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        // myCanvas = new Canvas(myBitmap);
        // 清除方法2：将位图清除为白色
        myBitmap.eraseColor(Color.TRANSPARENT);
        // 两种清除方法都必须加上后面这两步：
        // 路径重置
        myPath.reset();
        // 刷新绘制
        invalidate();
    }

}
