package com.jqorz.test.view.scale_view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 支持缩放的View
 *
 * @author jqorz
 * @since 2018/2/2.
 */

public class ZoomImageView extends View {
    /**
     * 原始状态
     */
    private static final int initStatus = 1;
    /**
     * 放大状态
     */
    private static final int blownUpStatus = 2;
    /**
     * 缩小状态
     */
    private static final int shrunkenStatus = 3;
    /**
     * 移动状态
     */
    private static final int moveStatus = 4;
    /**
     * 缩放操作
     */
    Matrix matrix = new Matrix();
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 当前装载Bitmap
     */
    private Bitmap currentBitmap;
    /**
     * 屏幕宽度
     */
    private int width;
    /**
     * 屏幕高度
     */
    private int height;
    /**
     * 记录当前图片的宽度，图片被缩放时，这个值会一起变动
     */
    private float currentBitmapWidth;

    ;
    /**
     * 记录当前图片的高度，图片被缩放时，这个值会一起变动
     */
    private float currentBitmapHeight;
    /**
     * 记录上次手指移动时的横坐标
     */
    private float lastXMove = -1;
    /**
     * 记录上次手指移动时的纵坐标
     */
    private float lastYMove = -1;
    /**
     * 当前状态
     */
    private int currentStatus;
    /**
     * 两指之间的中点坐标X
     */
    private int centerPointX;
    /**
     * 两指之间的中点坐标Y
     */
    private int centerPointY;
    /**
     * 之前两点距离
     */
    private float lastFingerDis;
    /**
     * 图片纵坐标平移量
     */
    private float totalTranslateY;
    /**
     * 图片横标平移量
     */
    private float totalTranslateX;
    /**
     * 记录图片原始缩放比例
     */
    private float initRatio;
    /**
     * 记录图片在矩阵上的总缩放比例
     */
    private float totalRatio;
    /**
     * 记录手指移动的距离所造成的缩放比例
     */
    private float scaledRatio;
    /**
     * 单指按下，横坐标
     */
    private int monodactylismX;
    /**
     * 单指按下，纵坐标
     */
    private int monodactylismY;
    /**
     * 单指移动距离   横坐标
     */
    private float movedDistanceX;
    /**
     * 单指移动距离  纵坐标
     */
    private float movedDistanceY;
    /**
     * 初始状态为NONE
     */
    private MODE mode = MODE.NONE;

    public ZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        currentStatus = initStatus;
    }

    public void setImageBitmap(Bitmap bm) {
        currentBitmap = bm;
        matrix.postRotate(30);// 图片旋转

        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            // 分别获取到ZoomImageView的宽度和高度
            width = getWidth();
            height = getHeight();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO 自动生成的方法存根
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //单指按下
                monodactylismBetweenFingers(event);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                lastFingerDis = distanceBetweenFingers(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    System.out.println("AAA");
                    mode = MODE.DRAG;
                    actionMove(event);
                } else if (event.getPointerCount() == 2) {
                    float currentFingerDis = distanceBetweenFingers(event);
                    // 获取当前两点中心坐标
                    centerPointBetweenFingers(event);
                    // 如果两指之间距离与之前两点之间距离相差小于4f,则为移动
                    if ((float) Math.abs(currentFingerDis - lastFingerDis) < 4f) {
                        mode = MODE.ZOOM;
                        actionMove(event);
                    } else {// 缩放操作

                        if (currentFingerDis > lastFingerDis) {
                            currentStatus = blownUpStatus;
                        } else {
                            currentStatus = shrunkenStatus;
                        }

                        // 边界判断，防止越界（缩放为原来的6倍）
                        if ((currentStatus == blownUpStatus && totalRatio < 6 * initRatio) || currentStatus == shrunkenStatus) {
                            scaledRatio = (float) (currentFingerDis / lastFingerDis);
                            totalRatio = totalRatio * scaledRatio;
                            if (totalRatio > 6 * initRatio) {
                                totalRatio = 6 * initRatio;
                            }
                            System.out.println("totalRatio:" + totalRatio);
                            invalidate();
                            lastFingerDis = currentFingerDis;
                        }

                    }
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode = MODE.NONE;
                lastXMove = -1;
                lastYMove = -1;
                // 当前图片比例小于原始图片比例，图片还原
                if (totalRatio < initRatio) {
                    totalRatio = initRatio;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                mode = MODE.NONE;
                lastXMove = -1;
                lastYMove = -1;
                break;

            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO 自动生成的方法存根
        super.onDraw(canvas);
        switch (currentStatus) {
            case initStatus:
                initBitmap(canvas);
                break;
            case blownUpStatus:
            case shrunkenStatus:
                actionZoom(canvas);
                break;
            case moveStatus:
                move(canvas);
                break;
            default:
                break;
        }
    }

    //位移
    private void move(Canvas canvas) {
        // TODO 自动生成的方法存根
        matrix.reset();
        float translationX = totalTranslateX + movedDistanceX;
        float translationY = totalTranslateY + movedDistanceY;
        matrix.postScale(totalRatio, totalRatio);
        matrix.postTranslate(translationX, translationY);
        totalTranslateX = translationX;
        totalTranslateY = translationY;
        canvas.drawBitmap(currentBitmap, matrix, null);
    }

    /**
     * 装载初始图片、获取初始数据
     */
    private void initBitmap(Canvas canvas) {

        if (currentBitmap != null) {
            matrix.reset();
            int bitmapWidth = currentBitmap.getWidth();
            int bitmapHeight = currentBitmap.getHeight();
            float translationX = 0f;
            float translationY = 0f;
            float ratio = 1.0f;
            //图片长宽大于屏幕长宽
            if (bitmapWidth > width || bitmapHeight > height) {
                //按宽度比例缩放
                if (bitmapWidth - width > bitmapHeight - height) {
                    //缩放比例
                    ratio = width / (bitmapWidth * 1.0f);
                    //图片居中显示时，图片位移量
                    translationY = (height - bitmapHeight * ratio) / 2f;
                    //图片缩放
                    matrix.postScale(ratio, ratio);
                    //居中显示
                    matrix.postTranslate(0, translationY);
                } else {
                    ratio = height / (bitmapHeight * 1.0f);
                    translationX = (width - bitmapWidth * ratio) / 2f;
                    matrix.postScale(ratio, ratio);
                    matrix.postTranslate(translationX, 0);
                }
            } else {
                translationX = (width - bitmapWidth) / 2f;
                translationY = (height - bitmapHeight) / 2f;
                matrix.postTranslate(translationX, translationY);
            }

            currentBitmapWidth = bitmapWidth * ratio;
            currentBitmapHeight = bitmapHeight * ratio;
            totalTranslateX = translationX;
            totalTranslateY = translationY;
            totalRatio = initRatio = ratio;
            // 重绘图片
            canvas.drawBitmap(currentBitmap, matrix, null);
        }
    }

    /**
     * 缩放
     */
    private void actionZoom(Canvas canvas) {
        matrix.reset();
        matrix.postScale(totalRatio, totalRatio);
        //图片长宽*总缩放比例获得当前图片长宽
        float scaledWidth = currentBitmap.getWidth() * totalRatio;
        float scaledHeight = currentBitmap.getHeight() * totalRatio;
        float translationX = 0f;
        float translationY = 0f;

        // 图片长宽小于屏幕长宽，则 以中点缩放
        if (currentBitmapWidth < width) {
            translationX = (width - scaledWidth) / 2.0f;
        } else {
            translationX = totalTranslateX * scaledRatio + centerPointX * (1 - scaledRatio);
            // 图片放大，整体向上移，所以translationX为负数（边界判断）
            if (translationX > 0) {
                translationX = 0;
            } else if (width - translationX > scaledWidth) {// 图片缩小，底部整体向上移，所以width-translationX > scaledWidth（边界判断）
                translationX = width - scaledWidth;
            }
        }

        if (currentBitmapHeight < height) {
            translationY = (height - scaledHeight) / 2.0f;
        } else {
            translationY = (totalTranslateY * scaledRatio + centerPointY * (1 - scaledRatio));
            // 图片放大，整体向上移，所以translationX为负数（边界判断）
            if (translationY > 0) {
                translationY = 0;
            } else if (height - translationY > scaledHeight) {// 图片缩小，底部整体向上移，所以width-translationX > scaledWidth（边界判断）
                translationY = height - scaledHeight;
            }
        }
        matrix.postTranslate(translationX, translationY);
        System.out.println("translationX:" + translationX + "translationY" + translationY);
        totalTranslateX = translationX;
        totalTranslateY = translationY;
        currentBitmapWidth = scaledWidth;
        currentBitmapHeight = scaledHeight;
        canvas.drawBitmap(currentBitmap, matrix, null);
    }

    private void actionMove(MotionEvent event) {
        float moveX = 0f, moveY = 0f;

        if (mode == MODE.DRAG) {
            if (currentBitmapWidth < width && currentBitmapHeight > height) {
                moveX = event.getX();
                moveY = event.getY();
                if (lastXMove == -1 || lastYMove == -1) {
                    lastXMove = moveX;
                    lastYMove = moveY;
                }
                movedDistanceY = moveY - lastYMove;
                moveBoundsChecking(moveX, moveY);
            } else if (currentBitmapWidth > width && currentBitmapHeight < height) {
                moveX = event.getX();
                moveY = event.getY();
                if (lastXMove == -1 || lastYMove == -1) {
                    lastXMove = moveX;
                    lastYMove = moveY;
                }
                movedDistanceX = moveX - lastXMove;
                moveBoundsChecking(moveX, moveY);
            }
        } else if (mode == MODE.ZOOM) {
            if (currentBitmapWidth > width && currentBitmapHeight > height) {
                moveX = centerPointX;
                moveY = centerPointY;
                if (lastXMove == -1 || lastYMove == -1) {
                    lastXMove = moveX;
                    lastYMove = moveY;
                }
                movedDistanceX = moveX - lastXMove;
                movedDistanceY = moveY - lastYMove;
                moveBoundsChecking(moveX, moveY);
            }
        }


    }

    //移动时对边界检查
    public void moveBoundsChecking(float moveX, float moveY) {
        if (totalTranslateX + movedDistanceX > 0) {//移动到最左边
            movedDistanceX = 0;
        } else if (width - (totalTranslateX + movedDistanceX) > currentBitmapWidth) {//移动到最右边
            movedDistanceX = 0;
        }

        if (totalTranslateY + movedDistanceY > 0) {//移动到最上边
            movedDistanceY = 0;
        } else if (height - (totalTranslateY + movedDistanceY) > currentBitmapHeight) {//移动到最下边
            movedDistanceY = 0;
        }
        // 平移标记
        currentStatus = moveStatus;
        invalidate(); // 调用OnDraw();
        lastXMove = moveX;
        lastYMove = moveY;
    }

    /**
     * 计算两个手指之间的距离。
     */
    private float distanceBetweenFingers(MotionEvent event) {
        float disX = Math.abs(event.getX(0) - event.getX(1));
        float disY = Math.abs(event.getY(0) - event.getY(1));
        return (float) Math.sqrt(disX * disX + disY * disY);
    }

    /**
     * 计算两个手指之间中心点的坐标。
     */
    private void centerPointBetweenFingers(MotionEvent event) {
        int current_xPoint0 = (int) event.getX(0);
        int current_yPoint0 = (int) event.getY(0);
        int current_xPoint1 = (int) event.getX(1);
        int current_yPoint1 = (int) event.getY(1);
        centerPointX = (current_xPoint0 + current_xPoint1) / 2;
        centerPointY = (current_yPoint0 + current_yPoint1) / 2;
    }

    /**
     * 计算单指按下时坐标
     *
     * @param event
     */
    public void monodactylismBetweenFingers(MotionEvent event) {
        monodactylismX = (int) event.getX();
        monodactylismY = (int) event.getY();
    }

    /**
     * 触摸状态的判断（三种状态：未触摸、拖动、缩放）
     */
    private enum MODE {
        NONE, DRAG, ZOOM
    }

    /**
     *
     */
    //public void


}
