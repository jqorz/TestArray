package com.jqorz.test.view.anim;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.jqorz.test.R;

/**
 * 属性动画
 */
public class AnimActivity extends AppCompatActivity {
    AnimatorSet rightAni, leftAni;
    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        mView = findViewById(R.id.view);
        rightAni = createAnimation(mView, true);
        leftAni = createAnimation(mView, false);

    }

    private AnimatorSet createAnimation(final View view, final boolean right) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new DecelerateInterpolator(2));
        animatorSet.play(
                ObjectAnimator.ofFloat(view, "translationX", right ? -dp2px(250) : 0, right ? 0 : -dp2px(250)).setDuration(500))
                .with(
                        ObjectAnimator.ofFloat(view, "alpha", (right ? 0 : 1.0f), (right ? 1.0f : 0)).setDuration(417));
        ;
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (right)
                    view.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!right)
                    view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        return animatorSet;
    }

    public void onClickBtn1(View view) {
        leftAni.start();

    }

    public void onClickBtn2(View view) {
        if (leftAni.isRunning()) {
            leftAni.cancel();
        }
        if (mView.getVisibility() != View.VISIBLE) {
            rightAni.start();
        }
    }

    /**
     * 不要使用此方法，因为dimen有很多个文件，使用此方法只会取得原始尺寸
     */
    public int dp2px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()));
    }

}
