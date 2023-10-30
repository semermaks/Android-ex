package com.example.sim;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sim.category.CategoriesAdapter;
import com.example.sim.dto.category.CategoryItemDTO;
import com.example.sim.service.ApplicationNetwork;
import com.example.sim.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    CategoriesAdapter adapter;
    RecyclerView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = findViewById(R.id.imageView);
        String url = "https://pv016.allin.ml/images/1.jpg";
        Glide.with(this)
                .load(url)
                .apply(new RequestOptions().override(600))
                .into(iv);

        rc = findViewById(R.id.rcvCategories);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false));

        rc.setAdapter(new CategoriesAdapter(new ArrayList<>(),
                MainActivity.this::onClickDeleteItem,
                MainActivity.this::onClickEditItem));
        requestServer();
    }

    void requestServer() {
        CommonUtils.showLoading();
        ApplicationNetwork
                .getInstance()
                .getJsonApi()
                .list()
                .enqueue(new Callback<List<CategoryItemDTO>>() {
                    @Override
                    public void onResponse(Call<List<CategoryItemDTO>> call, Response<List<CategoryItemDTO>> response) {
                        List<CategoryItemDTO> data = response.body();
                        adapter = new CategoriesAdapter(data,
                                MainActivity.this::onClickDeleteItem,
                                MainActivity.this::onClickEditItem);
                        rc.setAdapter(adapter);
                        CommonUtils.hideLoading();
                    }

                    @Override
                    public void onFailure(Call<List<CategoryItemDTO>> call, Throwable t) {
                        CommonUtils.hideLoading();
                    }
                });
    }

    private void onClickEditItem(CategoryItemDTO categoryItemDTO) {
        Toast.makeText(this, "Edit: "+categoryItemDTO.getId(), Toast.LENGTH_LONG).show();
    }
    private void onClickDeleteItem(CategoryItemDTO categoryItemDTO) {
        Toast.makeText(this, "DELETE: "+categoryItemDTO.getId(), Toast.LENGTH_LONG).show();
    }
}