package com.jqorz.test.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * 自定义RecyclerView的ItemDecoration
 * 以实现RecyclerView的GridLayout分组悬停
 */

public abstract class TitleItemDecoration extends RecyclerView.ItemDecoration {
    private static int COLOR_TITLE_BG;
    private static int COLOR_TITLE_FONT;

    private Paint mPaint;
    private Rect mBounds;//用于存放测量文字Rect
    private int mTitleHeight;//title的高

    protected TitleItemDecoration(Context context) {
        super();
        mPaint = new Paint();
        mBounds = new Rect();


        COLOR_TITLE_BG = Color.GRAY;
        COLOR_TITLE_FONT = Color.BLACK;
        mTitleHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        int mTitleFontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, context.getResources().getDisplayMetrics());
        mPaint.setTextSize(mTitleFontSize);
        mPaint.setAntiAlias(true);
    }


    /**
     * 计算当前位置是否应该有标题
     */
    public abstract boolean calculateShouldHaveHeader(int position);


    public abstract String getTag(int position);

    @Override//先调用 在绘制itemView之前绘制,此处为绘制每一个Title
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int position = parent.getChildLayoutPosition(child);
            //我记得Rv的item position在重置时可能为-1.保险点判断一下吧
            if (position > -1) {
                if (position == 0) {//等于0肯定要有title的
                    drawTitleArea(c, left, right, child, params, position);
                } else {//其他的通过判断
                    if (calculateShouldHaveHeader(position)) {
                        drawTitleArea(c, left, right, child, params, position);
                    }
                }
            }
        }
    }


    /**
     * 绘制Title区域背景和文字的方法
     */
    private void drawTitleArea(Canvas c, int left, int right, View child, RecyclerView.LayoutParams params, int position) {//最先调用，绘制在最下层
        String tag = getTag(position);
        mPaint.setColor(COLOR_TITLE_BG);
        c.drawRect(left, child.getTop() - params.topMargin - mTitleHeight, right, child.getTop() - params.topMargin, mPaint);
        mPaint.setColor(COLOR_TITLE_FONT);
        mPaint.getTextBounds(tag, 0, String.valueOf(tag).length(), mBounds);
        c.drawText(tag, 100, child.getTop() - params.topMargin - (mTitleHeight / 2 - mBounds.height() / 2), mPaint);
    }

    @Override//设置指定itemView的paddingLeft，paddingTop， paddingRight， paddingBottom
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildLayoutPosition(view);
//        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        //我记得Rv的item position在重置时可能为-1.保险点判断一下吧
        if (position > -1) {
            if (calculateShouldHaveHeader(position)) {
                outRect.set(0, mTitleHeight, 0, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }

        }
    }

}