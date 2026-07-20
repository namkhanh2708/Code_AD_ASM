package com.example.aimentor.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.aimentor.R;
import com.example.aimentor.activities.LoginActivity;
import com.example.aimentor.activities.MathlsActivity;
import com.example.aimentor.activities.SignUpActivity;
import com.example.aimentor.activities.categories.AddCategoryActivity;
import com.example.aimentor.adapters.CategoryListAdapter;
import com.example.aimentor.models.CategoryModel;
import com.example.aimentor.repository.CategoryRepository;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    private ArrayList<CategoryModel> categoryArrayList;
    private CategoryListAdapter categoryAdapter;
    private CategoryRepository categoryRepository;
    private RecyclerView recyclerView;
    private Button btnDelete;

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        
        Button btnAdd = view.findViewById(R.id.btnCreateCategory);
        btnDelete = view.findViewById(R.id.btnDelete);
        recyclerView = view.findViewById(R.id.rvCategory);
        
        categoryRepository = new CategoryRepository(getActivity());
        categoryArrayList = categoryRepository.getAllCategories();
        categoryAdapter = new CategoryListAdapter(categoryArrayList, getContext());

        categoryAdapter.setOnCategoryMenuListener(category -> {
            btnDelete.setVisibility(View.VISIBLE);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(categoryAdapter);

        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddCategoryActivity.class);
            startActivity(intent);
        });

        btnDelete.setOnClickListener(v -> {
            CategoryModel selected = categoryAdapter.getSelectedCategory();
            int position = categoryAdapter.getSelectedPosition();
            if (selected != null) {
                boolean isDeleted = categoryRepository.deleteCategory(selected.getId());
                if (isDeleted) {
                    categoryAdapter.removeCategory(position);
                    btnDelete.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "Category deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Failed to delete category", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
