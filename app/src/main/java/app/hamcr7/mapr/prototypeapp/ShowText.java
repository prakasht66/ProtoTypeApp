package app.hamcr7.mapr.prototypeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowText extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text);

        textView=findViewById(R.id.multiTxtView);

        Bundle bundle = getIntent().getExtras();
        String description = bundle.getString("content");

        textView.setText(description);

    }
}
