package chuang.propertyanimatordemo;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class LoginAlphaAnimation extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_alpha);

        mImageView = (ImageView) findViewById(R.id.imageview);

    }

    public void Alpha(View view) {
        Animator animator = AnimatorInflater.loadAnimator(LoginAlphaAnimation.this, R.animator.alpha_login);
        animator.setTarget(mImageView);
        animator.start();
    }
}
