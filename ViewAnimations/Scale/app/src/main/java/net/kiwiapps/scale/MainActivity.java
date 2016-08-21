package net.kiwiapps.scale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static final float[] STATE_LIST = {1.4f, 2.4f, 1.0f};
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
        float scaleTo = STATE_LIST[state];
        view.animate().scaleX(scaleTo).scaleY(scaleTo).start();
        state = state == STATE_LIST.length - 1 ? 0 : state + 1;
    }
}
