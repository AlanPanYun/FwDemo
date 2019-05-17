package com.example.fwdemo.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fwdemo.R;
import com.example.fwdemo.utils.TakePhotoUtils;

/**
 * @author Alan
 * @date 2018/6/19
 */
public class PicActivity extends AppCompatActivity {

    private TextView tvCamera;
    private TextView tvAlbum;

    public static final int IMAGE_REQUEST_CODE = 111;
    private ImageView ivPhoto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);

        tvCamera = (TextView) findViewById(R.id.tv_camera);
        tvAlbum = (TextView) findViewById(R.id.tv_album);
        ivPhoto = (ImageView) findViewById(R.id.iv_photo);

        initLisenter();
    }

    public void initLisenter() {
        tvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TakePhotoUtils.takePicByCamera(PicActivity.this,TakePhotoUtils.getPath());
            }
        });

        tvAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            case TakePhotoUtils.REQUEST_CODE_ALBUM://这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        ivPhoto.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace();
                    }
                }
                break;
            case TakePhotoUtils.REQUEST_CODE_CAMERA:

                break;
        }
    }
}
