package com.example.fwdemo.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.fwdemo.R
import com.face.library.utils.TakePhotoUtils

/**
 * @author Alan
 * @date 2018/6/19
 */
class PicActivity : AppCompatActivity() {

    private var tvCamera: TextView? = null
    private var tvAlbum: TextView? = null
    private var ivPhoto: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic)

        tvCamera = findViewById<View>(R.id.tv_camera) as TextView
        tvAlbum = findViewById<View>(R.id.tv_album) as TextView
        ivPhoto = findViewById<View>(R.id.iv_photo) as ImageView

        initLisenter()
    }

    fun initLisenter() {
        tvCamera!!.setOnClickListener { TakePhotoUtils.takePicByCamera(this@PicActivity, TakePhotoUtils.getPath()) }

        tvAlbum!!.setOnClickListener { }

        tvAlbum!!.setOnClickListener{

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        //在相册里面选择好相片之后调回到现在的这个activity中
        when (requestCode) {
            TakePhotoUtils.REQUEST_CODE_ALBUM//这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
            -> if (resultCode == Activity.RESULT_OK) {//resultcode是setResult里面设置的code值
                try {

                    val selectedImage = data?.data //获取系统返回的照片的Uri
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                    val cursor = contentResolver.query(selectedImage!!,
                            filePathColumn, null, null, null)//从系统表中查询指定Uri对应的照片
                    cursor!!.moveToFirst()
                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    val path = cursor.getString(columnIndex)  //获取照片路径
                    cursor.close()
                    val bitmap = BitmapFactory.decodeFile(path)
                    ivPhoto!!.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    // TODO Auto-generatedcatch block
                    e.printStackTrace()
                }

            }
            TakePhotoUtils.REQUEST_CODE_CAMERA -> {
            }
        }
    }

    companion object {

        val IMAGE_REQUEST_CODE = 111
    }
}
