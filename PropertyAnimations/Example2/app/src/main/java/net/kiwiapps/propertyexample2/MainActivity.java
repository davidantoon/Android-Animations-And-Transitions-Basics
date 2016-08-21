package net.kiwiapps.propertyexample2;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

public class MainActivity extends AppCompatActivity {


    private View fab;
    private View titlePanel;
    private View trackPanel;
    private int titleStartValue;
    private int trackStartValue;
    private int titleEndValue;
    private int trackEndValue;
    private boolean initialized;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        titlePanel = findViewById(R.id.title_panel);
        trackPanel = findViewById(R.id.track_panel);

        findViewById(R.id.try_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!initialized) {
                    titleStartValue = titlePanel.getTop();
                    titleEndValue = titlePanel.getBottom();
                    trackStartValue = trackPanel.getTop();
                    trackEndValue = trackPanel.getBottom();
                    initialized = true;
                }
                animateViews();
            }
        });
    }

    private void initScaleXY(View view) {
        view.setScaleX(0);
        view.setScaleY(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private AnimatorSet generateScaleAnimatorSet(View view) {

        ObjectAnimator scaleAnimX = ObjectAnimator.ofFloat(
                view, "scaleX", 0f, 1f);
        ObjectAnimator scaleAnimY = ObjectAnimator.ofFloat(
                view, "scaleY", 0f, 1f);

        AnimatorSet scaleSet = new AnimatorSet();
        scaleSet.playTogether(scaleAnimX, scaleAnimY);
        scaleSet.setInterpolator(new OvershootInterpolator());
        return scaleSet;
    }

    private ObjectAnimator generateBottomObjectAnimator(View view, int from, int to) {

        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(
                view, "bottom", from, to);
        objectAnimator.setInterpolator(new LinearInterpolator());
        return objectAnimator;
    }

    private void animateViews() {
        initScaleXY(fab);
        titlePanel.setBottom(titleStartValue);
        trackPanel.setBottom(trackStartValue);

        AnimatorSet fabAnimatorSet = generateScaleAnimatorSet(fab);

        ObjectAnimator titlePanelAnimator =
                generateBottomObjectAnimator(titlePanel, titleStartValue, titleEndValue);

        ObjectAnimator trackPanelAnimator =
                generateBottomObjectAnimator(trackPanel, trackStartValue, trackEndValue);

        AnimatorSet finalAnim = new AnimatorSet();
        finalAnim.playSequentially(titlePanelAnimator, trackPanelAnimator, fabAnimatorSet);
        finalAnim.start();
    }
}
