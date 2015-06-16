package demo.loadingview.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import demo.loadingview.*;

/**
 * Created by moon.zhong on 2015/3/10.
 */
public class LoadingLayout extends RelativeLayout {


    private View mCircleView;
    private View mRectView;
    private View mTriangleView;
    private ImageView mBottomView;
    private float mAnimTransValueRec[];
    private float mRotationValue[];
    private float mScaleX[];
    private AnimatorSet mCircleAnim;
    private AnimatorSet mRectAnim;
    private AnimatorSet mTriangleAnim;
    private Animator.AnimatorListener mCircleListener;
    private Animator.AnimatorListener mRectListener;
    private Animator.AnimatorListener mTriangleListener;

    private boolean isAnim = false;

    public LoadingLayout(Context context) {
        this(context, null);
    }

    public LoadingLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(getContext());
        initValue();
        initAnim();
    }

    private void initAnim() {
        mCircleListener = new CircleAnimListener();
        mRectListener = new RectAnimListener();
        mTriangleListener = new TriangleAnimListener();
    }

    private void initView(Context context) {
        /*固定这几个图片的大小为28个 dp 值*/
        int viewSize = (int) (28 * getResources().getDisplayMetrics().density + .5f);
        /*创建一个 显示圆形图片的View*/
        mCircleView = new View(context);
        /*设置参数*/
        RelativeLayout.LayoutParams circleParams = new LayoutParams(viewSize, viewSize);
        /*让他水平居中显示*/
        circleParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        mCircleView.setLayoutParams(circleParams);
        /*设置背景图片*/
        mCircleView.setBackgroundResource(R.mipmap.loading_yuan);
        /*设置 id，这里的作用，是为了下面阴影的排列，需要用此View 作为参考对象*/
        mCircleView.setId(R.id.action_bar_root);

        /*创建一个显示正方形图片的View*/
        mRectView = new View(context);
        RelativeLayout.LayoutParams rectParams = new LayoutParams(viewSize, viewSize);
        rectParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        mRectView.setLayoutParams(rectParams);
        mRectView.setBackgroundResource(R.mipmap.loading_fangxing);

        /*创建一个显示三角形图片的View*/
        mTriangleView = new View(context);
        RelativeLayout.LayoutParams triangleParams = new LayoutParams(viewSize, viewSize);
        triangleParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        mTriangleView.setLayoutParams(triangleParams);
        mTriangleView.setBackgroundResource(R.mipmap.loading_sanjiao);

        /*创建一个显示底部阴影图片的ImageView*/
        mBottomView = new ImageView(context);
        RelativeLayout.LayoutParams bottomParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        /*设置水平居中*/
        bottomParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        /*设置在圆形图片的下方*/
        bottomParams.addRule(RelativeLayout.BELOW, R.id.action_bar_root);
        mBottomView.setLayoutParams(bottomParams);
        mBottomView.setBackgroundResource(R.mipmap.loading_bottom);
        /*整个Layout 中的View 居中显示*/
        setGravity(Gravity.CENTER);
        /*添加View*/
        addView(mCircleView);
        addView(mRectView);
        addView(mTriangleView);
        addView(mBottomView);

        mRectView.setVisibility(INVISIBLE);
        mTriangleView.setVisibility(INVISIBLE);
    }

    private void initValue() {
        mAnimTransValueRec = new float[3];
        mAnimTransValueRec[0] = 0f;
        mAnimTransValueRec[1] = -150f;
        mAnimTransValueRec[2] = 0f;
        mScaleX = new float[11];
        mScaleX[0] = .9f;
        mScaleX[1] = .5f;
        mScaleX[2] = .2f;
        mScaleX[3] = .1f;
        mScaleX[4] = .05f;
        mScaleX[5] = .1f;
        mScaleX[6] = .2f;
        mScaleX[7] = .3f;
        mScaleX[8] = .5f;
        mScaleX[9] = .7f;
        mScaleX[10] = .9f;
        mRotationValue = new float[2];
        mRotationValue[0] = 0;
        mRotationValue[1] = 120.0f;
    }

    private void startAnim() {
        Log.v("zgy", "=========startAnim========");
        isAnim = true;
        if (mCircleView.getVisibility() != VISIBLE) {
            mCircleView.setVisibility(VISIBLE);
            mRectView.setVisibility(INVISIBLE);
            mTriangleView.setVisibility(INVISIBLE);
        }
        /*圆形图片的动画集合*/
        mCircleAnim = new AnimatorSet();
        /*设置执行时长800ms*/
        mCircleAnim.setDuration(800L);
        /*这里设置播放动画的个数，移动动画和底部阴影放缩动画*/
        mCircleAnim.playTogether(translationAnim(mCircleView), bottomAnim());
        /*开始动画*/
        mCircleAnim.start();
        /*设置动画监听事件*/
        mCircleAnim.addListener(mCircleListener);

        mRectAnim = new AnimatorSet();
        mRectAnim.setStartDelay(800L);
        mRectAnim.setDuration(800L);
        mRectAnim.playTogether(translationAnim(mRectView), bottomAnim(), rotationAnim(mRectView));
        mRectAnim.start();
        mRectAnim.addListener(mRectListener);

        mTriangleAnim = new AnimatorSet();
        mTriangleAnim.setStartDelay(1600L);
        mTriangleAnim.setDuration(800L);
        mTriangleAnim.playTogether(translationAnim(mTriangleView), bottomAnim(), rotationAnim(mTriangleView));
        mTriangleAnim.start();
        mTriangleAnim.addListener(mTriangleListener);
    }

    private Animator bottomAnim() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mBottomView, "scaleX", mScaleX);
        objectAnimator.setInterpolator(new DecelerateAccelerateInterpolator());
        return objectAnimator;
    }

    private Animator translationAnim(Object object) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(object, "translationY", mAnimTransValueRec);
        animator.setInterpolator(new demo.loadingview.view.DecelerateAccelerateInterpolator());
        return animator;
    }

    private Animator rotationAnim(Object object) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(object, "rotation", mRotationValue);
        return animator;
    }

    private void stopAnim() {

        if (mCircleAnim != null) {
            mCircleAnim.end();
            mCircleAnim.removeAllListeners();
        }
        if (mRectAnim != null) {
            mRectAnim.end();
            mRectAnim.removeAllListeners();
        }
        if (mTriangleAnim != null) {
            mTriangleAnim.end();
            mTriangleAnim.removeAllListeners();
        }
        isAnim = false;
    }

    private class CircleAnimListener extends AnimatorListenerAdapter {

        @Override
        public void onAnimationEnd(Animator animation) {
            mCircleView.setVisibility(INVISIBLE);
            mTriangleView.setVisibility(INVISIBLE);
            mRectView.setVisibility(VISIBLE);

        }
    }

    private class RectAnimListener extends AnimatorListenerAdapter {

        @Override
        public void onAnimationEnd(Animator animation) {
            mCircleView.setVisibility(INVISIBLE);
            mTriangleView.setVisibility(VISIBLE);
            mRectView.setVisibility(INVISIBLE);
        }
    }

    private class TriangleAnimListener extends AnimatorListenerAdapter {

        @Override
        public void onAnimationEnd(Animator animation) {
            mCircleView.setVisibility(VISIBLE);
            mTriangleView.setVisibility(INVISIBLE);
            mRectView.setVisibility(INVISIBLE);
            startAnim();
        }
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == VISIBLE) {
            if (!isAnim)
                startAnim();
        } else {
            stopAnim();
        }
    }
}
