/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jqorz.common.finger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FingerPointPaint extends AppCompatActivity {

    private Paint mPaint;
    private List<PointF> pointFS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

        mPaint = new Paint();
        mPaint.setColor(0xFFFF0000);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);


    }


    public class MyView extends View {

        private float mX, mY;

        public MyView(Context c) {
            super(c);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
        }

        @Override
        protected void onDraw(Canvas canvas) {
//        private void draw() {
//            canvas.drawColor(Color.WHITE);
            for (PointF p : pointFS) {
                canvas.drawCircle(p.x, p.y, 3, mPaint);
            }

//            canvas.drawPath(mPath, mPaint);
        }

        private void touch_start(float x, float y) {
            mX = x;
            mY = y;
            pointFS.add(new PointF(x, y));
        }

        private void touch_move(float x, float y) {
            mX = x;
            mY = y;
            pointFS.add(new PointF(x, y));

        }

        private void touch_up(float x, float y) {
            mX = x;
            mY = y;
            pointFS.add(new PointF(x, y));

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
                    touch_up(x, y);
                    invalidate();
                    break;
            }
            return true;
        }
    }
}
