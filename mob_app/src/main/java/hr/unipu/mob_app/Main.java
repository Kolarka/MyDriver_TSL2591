package hr.unipu.mob_app;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import de.hdodenhof.circleimageview.CircleImageView;

public class Main extends Fragment {
    Context ctx;
    public Main(){};


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.activity_main, container,false );
        ctx = getActivity();

        CircleImageView type1 = (CircleImageView) v.findViewById(R.id.circle3);
        CircleImageView type2 = (CircleImageView) v.findViewById(R.id.circle1);
        CircleImageView type3 = (CircleImageView) v.findViewById(R.id.circle2);
        CircleImageView type4 = (CircleImageView) v.findViewById(R.id.circle4);
        CircleImageView type5 = (CircleImageView) v.findViewById(R.id.circle5);


        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new UV();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("Option", 1 );
                fr.setArguments(args);
                ft.replace(R.id.FrameContainer, fr);
                ft.commit();


            }
        });
        type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new UV();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("Option", 2 );
                fr.setArguments(args);
                ft.replace(R.id.FrameContainer, fr);
                ft.commit();

            }
        });
        type3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new UV();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("Option", 1 );
                fr.setArguments(args);
                ft.replace(R.id.FrameContainer, fr);
                ft.commit();

            }
        });
        type4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new UV();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("Option", 4 );
                fr.setArguments(args);
                ft.replace(R.id.FrameContainer, fr);
                ft.commit();

            }
        });
        type5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fr = new UV();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Bundle args = new Bundle();
                args.putInt("Option", 5 );
                fr.setArguments(args);
                ft.replace(R.id.FrameContainer, fr);
                ft.commit();
            }
        } );

        return v;
    }
}

