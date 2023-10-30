package com.example.sim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.oginotihiro.cropview.CropUtil;
import com.oginotihiro.cropview.CropView;

import java.io.File;

public class ChangeImageActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    private CropView cropView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_image);
        cropView=findViewById(R.id.cropView);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Обрати фото"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode,data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK && data != null) {
                Uri selectImage = data.getData();
                cropView.of(selectImage).asSquare().initialize(this);
            }
            else {
                finish();
            }
            //TODO: action
        }
        else {
            finish();
        }
    }

    public void onClickRight(View view) {
        cropView.setRotation(cropView.getRotation()+90);
    }

    public void onClickLeft(View view) {
        cropView.setRotation(cropView.getRotation()-90);
    }

    public void onClickCrop(View view) {
        String fileTemp = java.util.UUID.randomUUID().toString();
        Bitmap croppedBitmap = cropView.getOutput();
        Matrix matrix = new Matrix();
        matrix.postRotate(cropView.getRotation());
        Bitmap rotatedBitmap = Bitmap.createBitmap(croppedBitmap, 0, 0, croppedBitmap.getWidth(), croppedBitmap.getHeight(), matrix, true);

        Uri fileSavePath = Uri.fromFile(new File(getCacheDir(), fileTemp));
        CropUtil.saveOutput(this, fileSavePath, rotatedBitmap, 90);

        //Вертаємо результат - Шлях до файлу, який збережено через кропер
        Intent intent = new Intent();
        intent.putExtra("croppedUri", fileSavePath);
        setResult(300, intent);
        finish();
    }
}