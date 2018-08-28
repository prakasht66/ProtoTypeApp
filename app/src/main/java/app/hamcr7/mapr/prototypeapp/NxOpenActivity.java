package app.hamcr7.mapr.prototypeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NxOpenActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    String[] listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nx_open);

        listView=findViewById(R.id.listView);
        textView=findViewById(R.id.textView);
        listItem = getResources().getStringArray(R.array.Topics);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, listItem);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // TODO Auto-generated method stub
                String value=adapter.getItem(position);
               // Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();

            }
        });


    }






}
