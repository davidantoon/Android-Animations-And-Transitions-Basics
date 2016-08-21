package net.kiwiapps.viewtransition2;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import static android.transition.Fade.IN;
import static android.transition.Fade.OUT;

public class MainActivity extends AppCompatActivity {


    private ViewGroup mContainer;
    private View mProfileAge;
    private View mProfilePosition;
    private View mProfilePhone;
    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_view);

        initViews();
    }

    private void initViews() {
        mContainer = (ViewGroup) findViewById(R.id.container);
        View profileContainer = findViewById(R.id.profile_container);
        mProfileAge = findViewById(R.id.profile_age);
        mProfilePosition = findViewById(R.id.profile_position);
        mProfilePhone = findViewById(R.id.profile_phone);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            profileContainer.setOnClickListener(minimizeExpandView2);
        }
    }

    private View.OnClickListener minimizeExpandView2 = new View.OnClickListener() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View view) {
            if (isExpanded) {
                minimizeView();
            } else {
                expandView();
            }
            isExpanded = !isExpanded;
            initViews();
        }
    };

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void minimizeView() {
        Scene transitionScene = Scene.getSceneForLayout(mContainer, R.layout.profile_view, MainActivity.this);

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);

        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(300);
        changeBounds.setInterpolator(new DecelerateInterpolator());


        Fade detailsFade = new Fade(OUT);
        detailsFade.addTarget(mProfileAge.getId());
        detailsFade.addTarget(mProfilePosition.getId());
        detailsFade.addTarget(mProfilePhone.getId());
        detailsFade.setDuration(200);

        transitionSet.addTransition(detailsFade);
        transitionSet.addTransition(changeBounds);


        TransitionManager.go(transitionScene, transitionSet);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void expandView() {
        Scene transitionScene = Scene.getSceneForLayout(mContainer, R.layout.profile_view_expanded, MainActivity.this);

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);

        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(500);
        changeBounds.setInterpolator(new OvershootInterpolator());

        Fade detailsFade = new Fade(IN);
        detailsFade.addTarget(mProfileAge.getId());
        detailsFade.addTarget(mProfilePosition.getId());
        detailsFade.addTarget(mProfilePhone.getId());
        detailsFade.setDuration(200);


        transitionSet.addTransition(changeBounds);
        transitionSet.addTransition(detailsFade);


        TransitionManager.go(transitionScene, transitionSet);
    }
}
