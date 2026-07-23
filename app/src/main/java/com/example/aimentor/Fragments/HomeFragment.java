package com.example.aimentor.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aimentor.R;
import com.example.aimentor.activities.MathlsActivity;
import com.example.aimentor.activities.ProgrammingActivity;

public class HomeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvWelcome = view.findViewById(R.id.tvWelcome);
        CardView cardProg = view.findViewById(R.id.cardProg);
        CardView cardMath = view.findViewById(R.id.cardMath);

        SharedPreferences sharedPf = getActivity().getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
        String username = sharedPf.getString("USERNAME_USER", "user"); 

        tvWelcome.setText("Hello " + username + "!");

        cardProg.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProgrammingActivity.class);
            startActivity(intent);
        });

        cardMath.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MathlsActivity.class);
            startActivity(intent);
        });

        return view;
    }
}