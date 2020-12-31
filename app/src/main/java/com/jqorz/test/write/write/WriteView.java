package com.jqorz.test.write.write;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;


/**
 * 用于测试可变粗细的圆滑画笔
 * 使用单个缓存点，粗细可变
 */
public class WriteView extends SurfaceView implements SurfaceHolder.Callback {
    private static final float TOUCH_TOLERANCE = 5.5f;
    // 画笔，定义绘制属性
    private Paint mPaint;
    // 绘制路径
    private Path mPath;
    // 画布及其底层位图
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private float paintWidthPrevious;//缓存上个path的画笔宽度
    private boolean isNew = true;//标记，如果是新的一笔，则不需要画笔渐变

    private SurfaceHolder surfaceHolder;

    private PointF point_last, point_middle;

    private VelocityTracker mVelocityTracker;

    public WriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setZOrderOnTop(true);
        this.getHolder().setFormat(PixelFormat.TRANSLUCENT);//去掉SurfaceView默认黑色背景
        init();
    }


    /**
     * 初始化工作
     */
    private void init() {

        // 绘制自由曲线用的画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeJoin(Paint.Join.ROUND);//设置圆弧连接
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置圆线帽
        mPaint.setStrokeWidth(12);
//        mPaint.setPathEffect(new CornerPathEffect(5));

        mPath = new Path();

        point_last = new PointF(0, 0);
        point_middle = new PointF(0, 0);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
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
                draw();
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
                draw();
                isNew = true;

                break;
        }
        return true;
    }

    private float getPaintWidth(float speed) {
        Log.i("jq", "speed = " + speed);
        float max_width = 15f;
        float min_width = 3f;

        float max_dif = 0.35f;//0.35f较为合适

        float max_speed = 30f;
        float min_speed = 1.5f;

        float width;

        speed = Math.max(min_speed, speed);
        speed = Math.min(max_speed, speed);

        //将 speed (min_speed-max_speed) 映射到 (width min_width-max_width)
//        width = (1 / (speed - min_speed) / (max_speed - min_speed)) * (max_width - min_width) + min_width;//反比例关系 速度越快 画笔越细
        width = (speed - min_speed) / (max_speed - min_speed) * (max_width - min_width) + min_width;//正比例关系 速度越快 画笔越粗

        Log.i("jq", "width = " + width);

        width = Math.max(min_width, width);
        width = Math.min(max_width, width);

        if (!isNew) {//如果不是新的一笔，则进行宽度渐变
            if (Math.abs(width - paintWidthPrevious) > max_dif) {
                if (width > paintWidthPrevious)
                    width = paintWidthPrevious + max_dif;
                else
                    width = paintWidthPrevious - max_dif;
            }
        }
        isNew = false;
        return width;
    }

    private float getSpeed(float xVelocity, float yVelocity) {
        return (float) Math.sqrt(Math.pow(xVelocity, 2) + (Math.pow(yVelocity, 2)));
    }

    //    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
    private void draw() {

        Canvas canvas = surfaceHolder.lockCanvas();
        //将路径绘制在自己的Bitmap上

        mCanvas.drawPath(mPath, mPaint);
        //将Bitmap绘制到界面上
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        mPath.reset();

        mPath.moveTo(point_last.x, point_last.y);

        // 直接绘制路径
//        canvas.drawPath(mPath, mPaint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }


    private void touch_start(float x, float y) {

        mPath.moveTo(x, y);

        point_last.x = (int) x;
        point_last.y = (int) y;

        point_middle.x = (int) x;
        point_middle.y = (int) y;


    }

    private void touch_move(float x, float y) {
        if (getUsefulPoint(x, y, point_middle.x, point_middle.y)) {


            mPath.quadTo(point_middle.x, point_middle.y, (x + point_middle.x) / 2, (y + point_middle.y) / 2);
            point_last.x = (x + point_middle.x) / 2;
            point_last.y = (y + point_middle.y) / 2;

            point_middle.x = x;
            point_middle.y = y;
            draw();

        }

    }

    private void touch_up(float x, float y) {
        mPath.lineTo(point_middle.x, point_middle.y);
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
        if (mBitmap != null) {
            mBitmap.eraseColor(Color.WHITE);
            // 两种清除方法都必须加上后面这两步：
            // 路径重置
            mPath.reset();
            // 刷新绘制
            draw();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);//绘制Bitamp的画布对象

        mBitmap.eraseColor(Color.WHITE);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
