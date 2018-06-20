package com.jqorz.test.anim;

import android.animation.Animator;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.jqorz.test.R;


/**
 * Created by lxz on 2017/3/6.
 */

public class CheerViewToast {
    private View mRootView;
    private ImageView mImg;
    private Toast mTast;
    private Animation animation;

    public CheerViewToast(Context context) {
        mTast = new Toast(context.getApplicationContext());
        mRootView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.dialog_cheerview, null);

        mRootView = findViewById(R.id.rootview);
        mImg = (ImageView) findViewById(R.id.img_cheerview);
        animation = AnimationUtils.loadAnimation(context, R.anim.praise_anim);

    }

    /**
     */
    public void show() {
        mRootView.post(new Runnable() {
            @Override
            public void run() {
                mImg.setAnimation(animation);
                mImg.setVisibility(View.VISIBLE);
                animation.start();
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mTast.cancel();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

        mTast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        mTast.setDuration(Toast.LENGTH_LONG);
        mTast.setView(mRootView);
        mTast.show();
    }

    private View findViewById(int id) {
        return mRootView.findViewById(id);
    }


}
