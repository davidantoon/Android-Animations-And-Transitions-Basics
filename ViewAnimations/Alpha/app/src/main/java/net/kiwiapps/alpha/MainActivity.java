package net.kiwiapps.alpha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static final float[] STATE_LIST = {0.7f, 0.0f, 1.0f};
    private int state = 0;

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
        float toAlpha = STATE_LIST[state];
        view.animate().alpha(toAlpha);
        // view.animate().alphaBy(toAlpha);
        state = state == STATE_LIST.length - 1 ? 0 : state + 1;
    }
}
