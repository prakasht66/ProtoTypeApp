package app.hamcr7.mapr.prototypeapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

import com.flaviofaria.kenburnsview.RandomTransitionGenerator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LearnFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LearnFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnFrag extends Fragment {
    CardView nxOpenCardView;
    LinearLayout mainLayout;
    Context  context;
    public LearnFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment_learn, container, false);
        nxOpenCardView=myView.findViewById(R.id.nxOpenCard);




        nxOpenCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = view.getContext();
                Intent myIntent = new Intent(context, NxOpenActivity.class);
                startActivity(myIntent);
            }
        });
       return  myView;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

//        mainLayout=view.findViewById(R.id.mainLay);
//        AnimationDrawable animationDrawable = (AnimationDrawable) mainLayout.getBackground();
//        animationDrawable.setEnterFadeDuration(2000);
//        animationDrawable.setExitFadeDuration(4000);
//        animationDrawable.start();
    }
}
