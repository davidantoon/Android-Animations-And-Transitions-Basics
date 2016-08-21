package net.kiwiapps.propertyexample1;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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

    private void animateViews() {
        if (!initialized) {
            titleStartValue = titlePanel.getTop();
            titleEndValue = titlePanel.getBottom();
            trackStartValue = trackPanel.getTop();
            trackEndValue = trackPanel.getBottom();
            initialized = true;
        }

        initScaleXY(fab);
        fab.animate().scaleX(1).scaleY(1).start();

        ObjectAnimator.ofInt(titlePanel, "bottom",
                titleStartValue, titleEndValue).start();

        ObjectAnimator.ofInt(trackPanel, "bottom",
                trackStartValue, trackEndValue).start();
    }
}
