package com.jqorz.test.view.dashboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.Keep;
import androidx.core.content.ContextCompat;

import com.jqorz.test.R;

import java.util.ArrayList;
import java.util.List;


/**
 * DashboardView为仿支付宝仪表盘的自定义view；DashboardView2，DashboardView3为github上原作者的代码，存在一些bug。
 * DashboardView根据2里面的属性动画，以3的样式增加了动画效果并修改了padding的bug
 */

public class DashboardView extends View {

    private int mRadius; // 画布边缘半径（去除padding后的半径）
    private int mStartAngle = 150; // 起始角度
    private int mHalfSweepAngle = 120; // 绘制角度的一半
    private int mMin = 0; // 仪表盘允许显示的最小值
    private int mMax = 100; // 仪表盘允许显示的最大值
    private int mSection = 50; // 值域（mMax-mMin）等分份数
    private String mHeaderText = ""; // 表头
    private int mCreditValue = 40; // 动画执行时的信用分
    private float mAngleWhenAnimation = 0; // 动画执行时的角度
    private int mSolidCreditValue = mCreditValue; // 固定的信用分(设定后不变)
    private int mSparkleWidth; // 亮点宽度
    private int mProgressWidth; // 进度圆弧宽度
    private float mLength1; // 刻度顶部相对边缘的长度
    private float mLength2; // 信用值指示器顶部相对边缘的长度
    private int mPadding;
    private float mCenterX, mCenterY; // 圆心坐标
    private Paint mPaint;
    private RectF mRectFProgressArc;//整个圆的外接正方形所在的矩形区域
    private Rect mRectText;
    private Path mPath;
    private boolean isAnimFinish = true;
    private int mBackgroundColor;
    private OnColorChangerListener onColorChangerListener;
    private List<DataBean> dataBeans;

    public DashboardView(Context context) {
        this(context, null);
    }

    public DashboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DashboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mSparkleWidth = dp2px(10);
        mProgressWidth = 4;

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setColor(Color.WHITE);

        mRectFProgressArc = new RectF();
        mRectText = new Rect();
        mPath = new Path();

        mBackgroundColor = ContextCompat.getColor(getContext(), R.color.green);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mPadding = Math.max(
                Math.max(getPaddingLeft(), getPaddingTop()),
                Math.max(getPaddingRight(), getPaddingBottom())
        );
        setPadding(mPadding, mPadding, mPadding, mPadding);

        mLength1 = mPadding + mSparkleWidth / 2f + dp2px(8);
        mLength2 = mLength1 + mProgressWidth + dp2px(4);

        int width = resolveSize(dp2px(220), widthMeasureSpec);
        mRadius = (width - mPadding * 2) / 2;

        setMeasuredDimension(width, width - dp2px(30));

        mCenterX = mCenterY = getMeasuredWidth() / 2f;
        mRectFProgressArc.set(
                mPadding + mSparkleWidth / 2f,
                mPadding + mSparkleWidth / 2f,
                getMeasuredWidth() - mPadding - mSparkleWidth / 2f,
                getMeasuredWidth() - mPadding - mSparkleWidth / 2f
        );

        mPaint.setTextSize(sp2px(12));
        mPaint.getTextBounds("0", 0, "0".length(), mRectText);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(mBackgroundColor);

        /*
          画进度圆弧背景
         */
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressWidth);
        mPaint.setAlpha(80);
        canvas.drawArc(mRectFProgressArc, mStartAngle, mHalfSweepAngle * 2, false, mPaint);

        mPaint.setAlpha(255);

        float angle = mAngleWhenAnimation - mStartAngle;
        /*
         * 画进度圆弧(起始到信用值)
         */
        mPaint.setShader(generateSweepGradient());
        canvas.drawArc(mRectFProgressArc, mStartAngle,
                angle, false, mPaint);
        /*
         * 画信用值指示亮点
         */
        float[] point = getCoordinatePoint(
                mRadius - mSparkleWidth / 2f,
                mStartAngle + angle
        );
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setShader(generateRadialGradient(point[0], point[1]));
        canvas.drawCircle(point[0], point[1], mSparkleWidth / 2f, mPaint);
        /*
          画刻度
         */
        int cnt = mSection / 2;//270°逆时针需要画的区域
        float degree = mHalfSweepAngle * 1f / cnt;//每个区域的角度
        float b = mHalfSweepAngle;
        mPaint.setShader(null);
        mPaint.setAlpha(bigOrEqualThan(angle, b) ? 200 : 80);
        canvas.save();
        //画270°位置的那个刻度
        canvas.drawLine(mCenterX, mLength1, mCenterX, mLength1 - 1, mPaint);
        // 从270°逆时针旋转
        for (int i = 0; i < cnt; i++) {
            canvas.rotate(-degree, mCenterX, mCenterY);
            b -= degree;
            mPaint.setAlpha(bigOrEqualThan(angle, b) ? 200 : 80);
            canvas.drawLine(mCenterX, mLength1, mCenterX, mLength1 - 1, mPaint);
        }
        canvas.restore();
        // 顺时针旋转
        canvas.save();
        b = mHalfSweepAngle;
        for (int i = 0; i < cnt; i++) {
            canvas.rotate(degree, mCenterX, mCenterY);
            b += degree;
            mPaint.setAlpha(bigOrEqualThan(angle, b) ? 200 : 80);
            canvas.drawLine(mCenterX, mLength1, mCenterX, mLength1 - 1, mPaint);
        }
        canvas.restore();

        /*
         * 画信用分指示器
         */
        canvas.save();
        b = mHalfSweepAngle;
        canvas.rotate(angle - b, mCenterX, mCenterY);
        mPaint.setAlpha(255);
        mPaint.setStyle(Paint.Style.FILL);
        mPath.reset();
        mPath.moveTo(mCenterX, mLength2);
        mPath.rLineTo(-dp2px(2), dp2px(5));
        mPath.rLineTo(dp2px(4), 0);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
        mPaint.setStrokeWidth(dp2px(1));
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mCenterX, mLength2 + dp2px(6) + 1, dp2px(2), mPaint);
        canvas.restore();

        /*
          画实时度数值
         */
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(sp2px(37));
        mPaint.setTextAlign(Paint.Align.CENTER);
        String value = String.valueOf(mCreditValue);
        canvas.drawText(value, mCenterX, mCenterY, mPaint);

        /*
          画信用描述
         */
        mPaint.setTextSize(sp2px(16));
        canvas.drawText(getDescriptionStr(), mCenterX, mCenterY - dp2px(40), mPaint);

        /*
          画表头
         */
        mPaint.setAlpha(160);
        mPaint.setTextSize(sp2px(12));
        canvas.drawText(mHeaderText, mCenterX, mCenterY - dp2px(65), mPaint);

        /*
          画评估时间
         */
        mPaint.setAlpha(160);
        mPaint.setTextSize(sp2px(11));
        canvas.drawText(getBottomTipStr(), mCenterX, mCenterY + dp2px(25), mPaint);
    }

    private boolean bigOrEqualThan(float a, float b) {
        return a + 0.001 >= b;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    private SweepGradient generateSweepGradient() {
        SweepGradient sweepGradient = new SweepGradient(mCenterX, mCenterY,
                new int[]{Color.argb(0, 255, 255, 255), Color.argb(200, 255, 255, 255)},
                new float[]{0, calculateRelativeAngleWithValue(mCreditValue) / 360}
        );
        Matrix matrix = new Matrix();
        matrix.setRotate(mStartAngle - 1, mCenterX, mCenterY);
        sweepGradient.setLocalMatrix(matrix);

        return sweepGradient;
    }

    private RadialGradient generateRadialGradient(float x, float y) {
        return new RadialGradient(x, y, mSparkleWidth / 2f,
                new int[]{Color.argb(255, 255, 255, 255), Color.argb(80, 255, 255, 255)},
                new float[]{0.4f, 1},
                Shader.TileMode.CLAMP
        );
    }

    /**
     * 根据半径和角度，换算点的（x,y）坐标
     */
    private float[] getCoordinatePoint(float radius, float angle) {
        float[] point = new float[2];

        double arcAngle = Math.toRadians(angle); //将角度转换为弧度
        if (angle < 90) {
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 90) {
            point[0] = mCenterX;
            point[1] = mCenterY + radius;
        } else if (angle > 90 && angle < 180) {
            arcAngle = Math.PI * (180 - angle) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY + Math.sin(arcAngle) * radius);
        } else if (angle == 180) {
            point[0] = mCenterX - radius;
            point[1] = mCenterY;
        } else if (angle > 180 && angle < 270) {
            arcAngle = Math.PI * (angle - 180) / 180.0;
            point[0] = (float) (mCenterX - Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        } else if (angle == 270) {
            point[0] = mCenterX;
            point[1] = mCenterY - radius;
        } else {
            arcAngle = Math.PI * (360 - angle) / 180.0;
            point[0] = (float) (mCenterX + Math.cos(arcAngle) * radius);
            point[1] = (float) (mCenterY - Math.sin(arcAngle) * radius);
        }

        return point;
    }

    /**
     * 相对起始角度计算信用分所对应的角度大小
     */
    private float calculateRelativeAngleWithValue(int value) {
        return mHalfSweepAngle * 2 * (value - mMin) * 1f / (mMax - mMin);
    }

    /**
     * 描述
     */
    private String getDescriptionStr() {
        for (DataBean data : dataBeans) {
            if (data.minValue <= mSolidCreditValue && mSolidCreditValue <= data.maxValue) {
                return data.description;
            }
        }
        return "";
    }

    /**
     * 底部的文字
     */
    private String getBottomTipStr() {
        for (DataBean data : dataBeans) {
            if (data.minValue <= mSolidCreditValue && mSolidCreditValue <= data.maxValue) {
                return data.bottomTip;
            }
        }
        return "";
    }

    private int getColorSize() {
        for (int i = 0; i < dataBeans.size(); i++) {
            DataBean data = dataBeans.get(i);
            if (data.minValue <= mSolidCreditValue && mSolidCreditValue <= data.maxValue) {
                return i + 1;
            }
        }
        return 1;
    }

    public int getCreditValue() {
        return mCreditValue;
    }

    public void setCreditValue(int creditValue) {
        if (mSolidCreditValue == creditValue || creditValue < mMin || creditValue > mMax) {
            return;
        }

        mSolidCreditValue = creditValue;
        mCreditValue = creditValue;
        postInvalidate();
    }

    public void setHeadText(String headText) {
        mHeaderText = headText;
        postInvalidate();
    }

    public void setDataBeans(List<DataBean> dataBeans) {
        this.dataBeans = dataBeans;
        postInvalidate();
    }

    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        postInvalidate();
    }

    public void setHalfSweepAngle(int mHalfSweepAngle) {
        this.mHalfSweepAngle = mHalfSweepAngle;
        postInvalidate();
    }

    public void setMin(int mMin) {
        this.mMin = mMin;
        postInvalidate();
    }

    public void setMax(int mMax) {
        this.mMax = mMax;
        postInvalidate();
    }

    public void setSection(int mSection) {
        this.mSection = mSection;
        postInvalidate();
    }

    public void setCreditValueWithAnim(int creditValue) {
        if (creditValue < mMin || creditValue > mMax || !isAnimFinish) {
            return;
        }

        mSolidCreditValue = creditValue;

        ValueAnimator creditValueAnimator = ValueAnimator.ofInt(mMin, mSolidCreditValue);
        creditValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCreditValue = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        //单独使用一个float值可以保证数值平滑过渡，动画流畅
        ValueAnimator angleValueAnimator = ValueAnimator.ofFloat(mStartAngle, mStartAngle + calculateRelativeAngleWithValue(mSolidCreditValue));
        angleValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAngleWhenAnimation = (float) animation.getAnimatedValue();
            }
        });
        // 实时信用值对应的背景色的变化
        int[] colors = new int[getColorSize()];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = ContextCompat.getColor(getContext(), dataBeans.get(i).colorId);
        }
        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(this, "mBackgroundColor", colors);
        long delay = 500 * (colors.length + 1);
        colorAnimator.setEvaluator(new ArgbEvaluator());
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBackgroundColor = (int) animation.getAnimatedValue();
                if (onColorChangerListener != null) {
                    onColorChangerListener.onColorChange(mBackgroundColor);
                }
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet
                .setDuration(delay)
                .playTogether(creditValueAnimator, angleValueAnimator, colorAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                isAnimFinish = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimFinish = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                isAnimFinish = true;
            }
        });
        animatorSet.start();
    }

    @Keep
    private void setMBackgroundColor(int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }

    public void setOnColorChangerListener(OnColorChangerListener onColorChangerListener) {
        this.onColorChangerListener = onColorChangerListener;
    }


    public interface OnColorChangerListener {
        void onColorChange(int color);
    }

    public static class Builder {
        private ArrayList<DataBean> dataBeans;
        private int mStartAngle = 150;
        private int mHalfSweepAngle = 120;
        private int mMin = 0; // 最小值
        private int mMax = 100; // 最大值
        private int mSection = 50;
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setDataBeans(ArrayList<DataBean> dataBeans) {
            this.dataBeans = dataBeans;
            return this;
        }

        public Builder setStartAngle(int mStartAngle) {
            this.mStartAngle = mStartAngle;
            return this;
        }

        public Builder setHalfSweepAngle(int mHalfSweepAngle) {
            this.mHalfSweepAngle = mHalfSweepAngle;
            return this;
        }

        public Builder setMin(int mMin) {
            this.mMin = mMin;
            return this;
        }

        public Builder setSection(int mSection) {
            this.mSection = mSection;
            return this;
        }

        public Builder setMax(int mMax) {
            this.mMax = mMax;
            return this;
        }

        public DashboardView create() {
            DashboardView dashboardView = new DashboardView(context);
            dashboardView.setDataBeans(dataBeans);
            dashboardView.setMax(mMax);
            dashboardView.setMin(mMin);
            dashboardView.setHalfSweepAngle(mHalfSweepAngle);
            dashboardView.setStartAngle(mStartAngle);
            dashboardView.setSection(mSection);
            return dashboardView;
        }
    }

    public static class DataBean {
        int minValue;
        int maxValue;
        String description;
        String bottomTip;
        @ColorRes
        int colorId;

        public DataBean(int minValue, int maxValue, String description, String bottomTip, int colorId) {
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.description = description;
            this.bottomTip = bottomTip;
            this.colorId = colorId;
        }
    }
}
