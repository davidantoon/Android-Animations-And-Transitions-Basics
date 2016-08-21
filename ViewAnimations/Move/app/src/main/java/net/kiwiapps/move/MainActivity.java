package net.kiwiapps.move;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.try_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateView(findViewById(R.id.animated_view));
            }
        });
    }

    private void animateView(View view) {
        float density = getResources().getDisplayMetrics().density;
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        view.setTranslationY(0);
        view.animate().translationY(300 * density)
                .setInterpolator(bounceInterpolator)
                .setDuration(1000);
    }
}
