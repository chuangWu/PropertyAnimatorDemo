package chuang.propertyanimatordemo;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class LoginTranslationAnim extends Activity {
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_translation);
        mImageView = (ImageView) findViewById(R.id.imageview);
    }

    public void Alpha(View view) {
        // Animator animator =
        // AnimatorInflater.loadAnimator(LoginTranslationAnim.this,
        // R.animator.translation_login);
        // animator.setTarget(mImageView);

        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "translationY", 100.0f, 0.0f);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(1000);
        animator.start();
    }
}
