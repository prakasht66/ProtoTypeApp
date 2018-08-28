package app.hamcr7.mapr.prototypeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;

public class TutorialActiviry extends AppCompatActivity {
    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_activiry);

        mainGrid = findViewById(R.id.mainGrid);
    }
}
