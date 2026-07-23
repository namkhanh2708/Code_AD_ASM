package com.example.aimentor.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.aimentor.R;
import com.example.aimentor.activities.MathlsActivity;
import com.example.aimentor.activities.ProgrammingActivity;

public class CategoryFragment extends Fragment {

    private ImageView imgDropDownProg, imgDropDownMath;
    private LinearLayout layoutDropDownProg, layoutDropDownMath;
    private Button btnProgLs1, btnMathLs1, btnMathLs2;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // Programming Category
        imgDropDownProg = view.findViewById(R.id.imgDropDownProg);
        layoutDropDownProg = view.findViewById(R.id.layoutDropDownProg);
        btnProgLs1 = view.findViewById(R.id.btnProgLs1);

        imgDropDownProg.setOnClickListener(v -> toggleLayout(layoutDropDownProg, imgDropDownProg));

        btnProgLs1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ProgrammingActivity.class);
            startActivity(intent);
        });

        // Math Category
        imgDropDownMath = view.findViewById(R.id.imgDropDownMath);
        layoutDropDownMath = view.findViewById(R.id.layoutDropDownMath);
        btnMathLs1 = view.findViewById(R.id.btnMathLs1);
        btnMathLs2 = view.findViewById(R.id.btnMathLs2);

        imgDropDownMath.setOnClickListener(v -> toggleLayout(layoutDropDownMath, imgDropDownMath));

        btnMathLs1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), MathlsActivity.class);
            startActivity(intent);
        });

        btnMathLs2.setOnClickListener(v -> {
            // Placeholder for Math Lesson 2
        });

        return view;
    }

    private void toggleLayout(LinearLayout layout, ImageView icon) {
        if (layout.getVisibility() == View.GONE) {
            layout.setVisibility(View.VISIBLE);
            icon.setImageResource(android.R.drawable.arrow_up_float);
        } else {
            layout.setVisibility(View.GONE);
            icon.setImageResource(android.R.drawable.arrow_down_float);
        }
    }
}
