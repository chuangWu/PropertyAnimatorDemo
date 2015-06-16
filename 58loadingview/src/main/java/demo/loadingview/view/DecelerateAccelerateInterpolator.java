package demo.loadingview.view;


import android.view.animation.Interpolator;

public final class DecelerateAccelerateInterpolator implements Interpolator {

    public final float getInterpolation(float input) {

        if (input < 0.2) {
            return - (input * (input/2.0f) -  input/2.0f);
        }else {
            return 1.0F + (input * (2.0F * input) - 2.0F * input) ;
        }
    }
}