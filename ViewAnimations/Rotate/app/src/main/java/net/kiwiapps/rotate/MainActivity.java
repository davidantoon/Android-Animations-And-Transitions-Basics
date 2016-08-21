package net.kiwiapps.rotate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean toggle = false;

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
        float rotateTo = toggle ? 0f : 135f;
        view.animate().rotation(rotateTo);
//        view.animate().rotationX(rotateTo);
//        view.animate().rotationY(rotateTo);
        toggle = !toggle;
    }
}
