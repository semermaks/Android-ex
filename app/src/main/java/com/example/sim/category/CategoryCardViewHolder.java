package com.example.sim.category;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sim.R;

public class CategoryCardViewHolder extends RecyclerView.ViewHolder {
    private ImageView categoryImage;
    private TextView categoryName;
    public Button edit_btn;
    public Button delete_btn;


    public CategoryCardViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryName=itemView.findViewById(R.id.categoryName);
        categoryImage=itemView.findViewById(R.id.categoryImage);
        edit_btn=itemView.findViewById(R.id.edit_btn);
        delete_btn=itemView.findViewById(R.id.delete_btn);
    }

    public ImageView getCategoryImage() {
        return categoryImage;
    }

    public TextView getCategoryName() {
        return categoryName;
    }
}
