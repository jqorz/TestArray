package com.jqorz.common.write.write;

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
 * 使用draw
 * 使用单个缓存点，粗细可变
 */
public class WriteView3 extends SurfaceView implements SurfaceHolder.Callback {
    private static final float TOUCH_TOLERANCE = 5.5f;
    // 画笔，定义绘制属性
    private Paint mPaint;
    private Paint pointPaintRed;
    private Paint pointPaintBlue;
    // 绘制路径
    private Path mPath;//主Path
    // 画布及其底层位图
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private float paintWidthPrevious = 50f;//缓存上个path的画笔宽度
    private boolean isNew = true;//标记，如果是新的一笔，则不需要画笔渐变
    private PointF[] mBoundPoints = new PointF[6];
    private PointF[] mCenterPoints = new PointF[3];
    private SurfaceHolder surfaceHolder;

    private PointF point_temp, point_last;

    private VelocityTracker mVelocityTracker;

    public WriteView3(Context context, AttributeSet attrs) {
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
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);//设置画笔填充

//        mPaint.setStrokeJoin(Paint.Join.ROUND);//设置圆弧连接
        mPaint.setStrokeCap(Paint.Cap.ROUND);//设置圆线帽
        mPaint.setStrokeWidth(12);
//        mPaint.setPathEffect(new CornerPathEffect(5));

        pointPaintRed = new Paint();
        pointPaintRed.setAntiAlias(true);
        pointPaintRed.setDither(true);
        pointPaintRed.setColor(Color.RED);
        pointPaintRed.setStrokeWidth(12);
        pointPaintRed.setTextSize(50);

        pointPaintBlue = new Paint();
        pointPaintBlue.setAntiAlias(true);
        pointPaintBlue.setDither(true);
        pointPaintBlue.setColor(Color.BLUE);
        pointPaintBlue.setStrokeWidth(12);
        pointPaintBlue.setTextSize(50);

        mPath = new Path();

        point_temp = new PointF(0, 0);
        point_last = new PointF(0, 0);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
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
                draw();
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.computeCurrentVelocity(10);
                float velocityX = mVelocityTracker.getXVelocity();
                float velocityY = mVelocityTracker.getYVelocity();

//                float speed = (getSpeed(mVelocityTracker.getXVelocity(),
//                        mVelocityTracker.getYVelocity()) / 2f);
//                paintWidthPrevious = getPaintWidth(speed);
//                mPaint.setStrokeWidth(paintWidthPrevious);
                touch_move(x, y, velocityX, velocityY);
                draw();

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
//        Log.i("jq", "speed = " + speed);
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

//        Log.i("jq", "width = " + width);

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

        for (int i = 0; i < mBoundPoints.length; i++) {//绘制边界特征点
            PointF p = mBoundPoints[i];
            if (p != null) {
                mCanvas.drawPoint(p.x, p.y, pointPaintRed);
//                mCanvas.drawText(String.valueOf(i), p.x, p.y, pointPaintBlue);
            }
        }

        for (int i = 0; i < mCenterPoints.length; i++) {//绘制边界特征点
            PointF p = mCenterPoints[i];
            if (p != null) {
                mCanvas.drawPoint(p.x, p.y, pointPaintBlue);
//                mCanvas.drawText(String.valueOf(i), p.x, p.y, pointPaintRed);
            }
        }
        mCanvas.drawPath(mPath, mPaint);
        //将Bitmap绘制到界面上
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

        mPath.reset();

        mPath.moveTo(point_temp.x, point_temp.y);

        // 直接绘制路径
//        canvas.drawPath(mPath, mPaint);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }


    private void touch_start(float x, float y) {

//        mPath.moveTo(x, y);
        mCanvas.drawCircle(x,y,paintWidthPrevious,mPaint);

        point_temp.x = (int) x;
        point_temp.y = (int) y;

        point_last.x = (int) x;
        point_last.y = (int) y;


    }

    private void touch_move(float x, float y, float velocityX, float velocityY) {
        if (getUsefulPoint(x, y, point_last.x, point_last.y)) {

            //Path目前停留在point_temp.x, point_temp.y

            mCenterPoints[0] = new PointF(point_temp.x, point_temp.y); //起始点为point_temp.x, point_temp.y
            mCenterPoints[1] = new PointF(point_last.x, point_last.y); //控制点为point_last.x,point_last.y
            mCenterPoints[2] = new PointF((x + point_last.x) / 2, (y + point_last.y) / 2);//结束点为(point_last.x+x)/2,(point_last.y+y)/2

//            double st = Math.abs(Math.atan(velocityY / velocityX));//新点的瞬时速度的角度 弧度制
            //弧度
            double s0 = computeAxeAngle(mCenterPoints[1], mCenterPoints[0]);//起始点到控制点
            double s1 = computeAxeAngle(mCenterPoints[2], mCenterPoints[0]);//起始点到终止点
            double s2 = computeAxeAngle(mCenterPoints[2], mCenterPoints[1]);//控制点到终止点

            DoublePointF lastDoublePointF = convert(mCenterPoints[0].x, mCenterPoints[0].y, paintWidthPrevious, s0);//前一个点的宽线坐标
            DoublePointF controlPointF = convert(mCenterPoints[1].x, mCenterPoints[1].y, paintWidthPrevious, s1);
            DoublePointF currentDoublePointF = convert(mCenterPoints[2].x, mCenterPoints[2].y, paintWidthPrevious, s2);//当前点的宽线坐标

            mBoundPoints[0] = lastDoublePointF.getPointF1();
            mBoundPoints[1] = lastDoublePointF.getPointF2();
            mBoundPoints[2] = controlPointF.getPointF1();
            mBoundPoints[3] = controlPointF.getPointF2();
            mBoundPoints[4] = currentDoublePointF.getPointF1();
            mBoundPoints[5] = currentDoublePointF.getPointF2();

            //从缓存点连接到该点的宽线坐标的一端
            mPath.lineTo(mBoundPoints[0].x, mBoundPoints[0].y);
            //绘制一侧的贝塞尔曲线
//            mPath.lineTo(currentDoublePointF.getPointF1().x, currentDoublePointF.getPointF1().y);
            mPath.quadTo(mBoundPoints[2].x, mBoundPoints[2].y, mBoundPoints[4].x, mBoundPoints[4].y);
            //封闭当前点的一端
            mPath.lineTo(mBoundPoints[5].x, mBoundPoints[5].y);
            //绘制另一侧的贝塞尔曲线
//            mPath.lineTo(lastDoublePointF.getPointF2().x, lastDoublePointF.getPointF2().y);
            mPath.quadTo(mBoundPoints[3].x, mBoundPoints[3].y, mBoundPoints[1].x, mBoundPoints[1].y);
            //封闭缓存点的一端，结束
            mPath.lineTo(mCenterPoints[0].x, mCenterPoints[0].y);

//  mPath.quadTo(point_last.x, point_last.y, (x + point_last.x) / 2, (y + point_last.y) / 2);


            point_temp.x = (x + point_last.x) / 2;
            point_temp.y = (y + point_last.y) / 2;

            point_last.x = x;
            point_last.y = y;

        }

    }


    /**
     * 计算两个移动点的方向角
     *
     * @return 弧度
     */
    private double computeAxeAngle(PointF point0, PointF point1) {
        return Math.atan2(point1.y - point0.y, point1.x - point0.x);
    }

    /**
     * 将原始坐标转换为宽线坐标
     *
     * @param x       原始点坐标x
     * @param y       原始点坐标y
     * @param width   扩宽的线的宽度
     * @param radians 位移弧度
     */
    private DoublePointF convert(float x, float y, float width, double radians) {

        Log.i("jq", "radians = " + radians + "        degrees = " + Math.toDegrees(radians));

        double last_x1 = x - width * Math.sin(radians);
        double last_y1 = y + width * Math.cos(radians);

        double last_x2 = x + width * Math.sin(radians);
        double last_y2 = y - width * Math.cos(radians);

        return new DoublePointF(new PointF((float) last_x1, (float) last_y1), new PointF((float) last_x2, (float) last_y2));
    }

    private void touch_up(float x, float y) {
        mPath.lineTo(point_last.x, point_last.y);
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
        mBitmap.eraseColor(Color.WHITE);
        // 两种清除方法都必须加上后面这两步：
        // 路径重置
        mPath.reset();
        mCenterPoints = new PointF[mCenterPoints.length];
        mBoundPoints = new PointF[mBoundPoints.length];
        // 刷新绘制
        draw();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    class DoublePointF {
        private PointF pointF1;
        private PointF pointF2;

        public DoublePointF(PointF pointF1, PointF pointF2) {
            this.pointF1 = pointF1;
            this.pointF2 = pointF2;
        }

        public PointF getPointF1() {
            return pointF1;
        }

        public PointF getPointF2() {
            return pointF2;
        }
    }
}
