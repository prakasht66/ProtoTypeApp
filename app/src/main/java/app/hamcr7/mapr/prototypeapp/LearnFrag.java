package app.hamcr7.mapr.prototypeapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LearnFrag.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LearnFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearnFrag extends Fragment {

    Context context;


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
        return inflater.inflate(R.layout.fragment_learn, container, false);
        //View view = inflater.inflate(R.layout.fragment_learn, container, false);
        //context = view.getContext();
        //Intent myIntent = new Intent(context, TutorialActiviry.class);
        //startActivity(myIntent);
        //return view;
    }

}
