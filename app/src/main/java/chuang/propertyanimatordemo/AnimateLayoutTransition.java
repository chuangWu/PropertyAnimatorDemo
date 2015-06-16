package chuang.propertyanimatordemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class AnimateLayoutTransition extends Activity {

    private LinearLayout ll;
    private LayoutTransition mTransition = new LayoutTransition();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_layout_transition);

        ll = (LinearLayout) findViewById(R.id.ll);
        setupCustomAnimations();
        ll.setLayoutTransition(mTransition);
    }

    public void add(View view) {
        final Button button = new Button(this);
        ll.addView(button);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                ll.removeView(button);
            }
        });
    }

    // 生成自定义动画
    private void setupCustomAnimations() {
        // 动画：CHANGE_APPEARING
        // Changing while Adding
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left", 0, 1);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top", 0, 1);
        PropertyValuesHolder pvhRight = PropertyValuesHolder.ofInt("right", 0,
                1);
        PropertyValuesHolder pvhBottom = PropertyValuesHolder.ofInt("bottom",
                0, 1);
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("scaleX",
                1f, 0f, 1f);
        PropertyValuesHolder pvhScaleY = PropertyValuesHolder.ofFloat("scaleY",
                1f, 0f, 1f);

        final ObjectAnimator changeIn = ObjectAnimator.ofPropertyValuesHolder(
                this, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhScaleX,
                pvhScaleY).setDuration(
                mTransition.getDuration(LayoutTransition.CHANGE_APPEARING));
        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, changeIn);
        changeIn.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator anim) {
                View view = (View) ((ObjectAnimator) anim).getTarget();
                // View也支持此种动画执行方式了
                view.setScaleX(1f);
                view.setScaleY(1f);
            }
        });


    }

    public void method(){

    }
}
