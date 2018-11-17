package app.hamcr7.mapr.prototypeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.flaviofaria.kenburnsview.KenBurnsView;

public class KenburnsTest extends AppCompatActivity {

    KenBurnsView kenBurnsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kenburns_test);

        kenBurnsView=findViewById(R.id.kenText);
        kenBurnsView.resume();
    }
}
