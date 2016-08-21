package net.kiwiapps.viewtransition;

import android.animation.AnimatorSet;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import static android.transition.Fade.IN;
import static android.transition.Fade.OUT;

public class MainActivity extends AppCompatActivity {


    private ViewGroup mContainer;
    private View mProfileContainer;
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);

        initViews();
    }

    private void initViews() {
        mContainer = (ViewGroup) findViewById(R.id.container);
        mProfileContainer = findViewById(R.id.profile_container);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mProfileContainer.setOnClickListener(minimizeExpandView1);
        }
    }

    private View.OnClickListener minimizeExpandView1 = new View.OnClickListener() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {
            Scene transitionScene;
            if (isExpanded) {
                transitionScene = Scene.getSceneForLayout(mContainer, R.layout.profile_view, view.getContext());
            } else {
                transitionScene = Scene.getSceneForLayout(mContainer, R.layout.profile_view_expanded, view.getContext());
            }
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.setDuration(600);
            TransitionManager.go(transitionScene, changeBounds);
            isExpanded = !isExpanded;
            initViews();
        }
    };
}
