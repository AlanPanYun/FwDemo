package com.face.library.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * @author Alan
 * @date 2018/6/19
 */
public class TakePhotoUtils {

    public static final int REQUEST_CODE_CAMERA = 111;
    public static final int REQUEST_CODE_ALBUM = 110;


    public static File getPath(){
        String s = Environment.getExternalStorageDirectory() + "/fwdemo/";
        File file = new File(s);
        if (!file.exists()) {
            file.mkdirs();
        }

        return new File(file + DateUtils.getCurrentTime() + ".png");
    }

    public static void takePicByCamera(Activity activity, File fileUri) {

        Uri imageUri = Uri.fromFile(fileUri);
        //通过FileProvider创建一个content类型的Uri
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            imageUri = FileProvider.getUriForFile(
                    activity,
                    activity.getPackageName() + ".fileprovider",
                    fileUri);
        }

        //调用系统相机
        Intent intentCamera = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intentCamera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intentCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //将拍照结果保存至photo_file的Uri中，不保留在相册中
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intentCamera, REQUEST_CODE_CAMERA);

    }

    public static void takePicByAlbum(Activity activity){
        //在这里跳转到手机系统相册里面
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, REQUEST_CODE_ALBUM);
    }
}
