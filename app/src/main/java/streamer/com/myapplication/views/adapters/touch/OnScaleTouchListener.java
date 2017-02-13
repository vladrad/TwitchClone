package streamer.com.myapplication.views.adapters.touch;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Vladi on 2/12/17.
 */

public class OnScaleTouchListener implements View.OnTouchListener {


    private View scaleView;

    public GestureDetector click;

    public OnScaleTouchListener(View scaleView, Context context) {
        this.scaleView = scaleView;
        scaleView.setScaleY(1f);//always start scaled
        scaleView.setScaleX(1f);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                scaleDown();
                break;
            case MotionEvent.ACTION_UP:
                scaleUp();
                break;
            default:
                scaleUp();
        }

        return false; // return false so on click handler can be handled
    }


    //Found this on stack over flow after a quick search
    public void scaleUp() {
        ObjectAnimator scaleDownX2 = ObjectAnimator.ofFloat( //on default touch up we got scaleX
                scaleView, "scaleX", 1f);
        ObjectAnimator scaleDownY2 = ObjectAnimator.ofFloat(
                scaleView, "scaleY", 1f);
        scaleDownX2.setDuration(250);
        scaleDownY2.setDuration(250);

        AnimatorSet scaleDown2 = new AnimatorSet();
        scaleDown2.play(scaleDownX2).with(scaleDownY2);

        scaleDown2.start(); // move back up after
    }

    public void scaleDown() {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(scaleView, //on touch down set the scale of xy
                "scaleX", 0.95f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(scaleView,
                "scaleY", 0.95f);
        scaleDownX.setDuration(250); // zoom speed
        scaleDownY.setDuration(250);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);//begin scale down

        scaleDown.start();
    }
}
