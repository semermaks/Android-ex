package com.example.sim.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sim.R;
import com.example.sim.application.HomeApplication;
import com.example.sim.contants.Urls;
import com.example.sim.dto.category.CategoryItemDTO;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryCardViewHolder> {

    private List<CategoryItemDTO> categories;
    private final OnItemClickListener delCat;
    private final OnItemClickListener editCat;

    public CategoriesAdapter(List<CategoryItemDTO> categories,
                             OnItemClickListener delCat,
                             OnItemClickListener editCat) {
        this.categories = categories;
        this.delCat = delCat;
        this.editCat = editCat;
    }

    @NonNull
    @Override
    public CategoryCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.category_view, parent, false);
        return new CategoryCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryCardViewHolder holder, int position) {
        if(categories!=null && position<categories.size())
        {
            CategoryItemDTO item = categories.get(position);
            holder.getCategoryName().setText(item.getName());
            String url = Urls.BASE+item.getImage();
            Glide.with(HomeApplication.getAppContext())
                    .load(url)
                    .apply(new RequestOptions().override(600))
                    .into(holder.getCategoryImage());
            holder.edit_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editCat.onItemClick(item);
                }
            });

            holder.delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delCat.onItemClick(item);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
