package com.example.aimentor.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aimentor.R;
import com.example.aimentor.activities.MathlsActivity;
import com.example.aimentor.activities.ProgrammingActivity;
import com.example.aimentor.models.CategoryModel;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryItemViewHolder> {

    public ArrayList<CategoryModel> categoryModels;
    public Context context;
    private int selectedPosition = -1;

    public CategoryListAdapter(ArrayList<CategoryModel> model, Context myContext) {
        categoryModels = model;
        context = myContext;
    }

    @NonNull
    @Override
    public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list, parent, false);
        return new CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {
        CategoryModel model = categoryModels.get(position);
        holder.tvCategoryName.setText(model.getCategoryName());
        holder.tvCreatedAt.setText(String.format("Created at: %s", model.getCreatedAt()));

        // Toggle dropdown
        holder.imgDropDown.setOnClickListener(v -> {
            if (holder.layoutDropDown.getVisibility() == View.GONE) {
                holder.layoutDropDown.setVisibility(View.VISIBLE);
                holder.imgDropDown.setImageResource(android.R.drawable.arrow_up_float);
            } else {
                holder.layoutDropDown.setVisibility(View.GONE);
                holder.imgDropDown.setImageResource(android.R.drawable.arrow_down_float);
            }
        });

        // Lesson navigation
        if (model.getCategoryName().equalsIgnoreCase("Programming")) {
            holder.btnLs2.setVisibility(View.GONE);
        } else {
            holder.btnLs2.setVisibility(View.VISIBLE);
        }

        holder.btnLs1.setOnClickListener(v -> {
            Intent intent;
            if (model.getCategoryName().equalsIgnoreCase("Programming")) {
                intent = new Intent(context, ProgrammingActivity.class);
            } else if (model.getCategoryName().equalsIgnoreCase("Discrete Mathematics")) {
                intent = new Intent(context, MathlsActivity.class);
            } else {
                intent = new Intent(context, MathlsActivity.class);
            }
            context.startActivity(intent);
        });

        holder.btnLs2.setOnClickListener(v -> {
            // Placeholder for Lesson 2
        });

        // Selection style logic
        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        holder.itemView.setOnClickListener(v -> {
            int previousSelected = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(previousSelected);
            notifyItemChanged(selectedPosition);
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public static class CategoryItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName, tvCreatedAt;
        ImageView imgDropDown;
        LinearLayout layoutDropDown;
        Button btnLs1, btnLs2;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvNameCategory);
            tvCreatedAt = itemView.findViewById(R.id.tvTimeCategory);
            imgDropDown = itemView.findViewById(R.id.imgDropDown);
            layoutDropDown = itemView.findViewById(R.id.layoutDropDown);
            btnLs1 = itemView.findViewById(R.id.btnLs1Dm);
            btnLs2 = itemView.findViewById(R.id.btnLs2Dm);
        }
    }
}
