package app.hamcr7.mapr.prototypeapp;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class TutorialActiviry extends AppCompatActivity {
    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_activiry);

        mainGrid = findViewById(R.id.mainGrid);
        LinearLayout mainLayOut=findViewById(R.id.tutMainLay);
        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayOut.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

    }
}
