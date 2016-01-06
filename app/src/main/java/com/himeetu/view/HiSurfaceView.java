package com.himeetu.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.himeetu.event.PhotoEvent;
import com.himeetu.util.LogUtil;
import com.himeetu.util.PhotoUtil;
import com.himeetu.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.greenrobot.event.EventBus;

/**
 * Created by object1984 on 16/1/5.
 */
public class HiSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Camera.PictureCallback {
    private static final int SURFAVCE_WIDTH = 1920;
    private static final int SURFAVCE_HEIGHT = 1080;
    private static final int PICTURE_WIDTH = 1280;
    private static final int PICTURE_HEIGHT= 720;
    private Camera camera = null;
    private SurfaceHolder surfaceHolder = null;

    public HiSurfaceView(Context context) {
        super(context);
        this.camera = camera;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try{

                camera = Camera.open(); // 打开摄像头

                if(camera == null){
                    return;
                }

                camera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象
//                parameters = camera.getParameters();
//                parameters.setJpegQuality(100); // 设置照片质量
                camera.setDisplayOrientation(90);
                camera.startPreview(); // 开始预览

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        //根本没有可处理的SurfaceView
        if (surfaceHolder.getSurface() == null) {
            return;
        }
        //这里可以做一些我们要做的变换。
        if (camera == null) {
            ToastUtil.show("没有开启照相权限，请到手机安全中心中开启。");
            return;
        }
        //先停止Camera的预览
        try {
            camera.stopPreview();

            Camera.Parameters parameters = camera.getParameters();// 获得相机参数

            parameters.setPictureSize(PICTURE_WIDTH, PICTURE_HEIGHT);
            parameters.setPictureFormat(PixelFormat.JPEG); // 设置照片格式
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            camera.setParameters(parameters);// 设置相机参数
            camera.startPreview();
            //重新开启Camera的预览功能
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
//        if ( camera != null) {
//            camera.stopPreview();
//            camera.release();
//            camera = null;
//        }
    }

    // 拍照
    public void takePicture() {
        if ( camera != null) {
            camera.takePicture(null, null,  this);
        }
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        new SaveBitmapThread(data).start();
    }

    class SaveBitmapThread extends Thread {
        private byte[] data;

        public SaveBitmapThread(byte[] data) {
            this.data = data;
        }

        @Override
        public void run() {
            super.run();

            Uri uri = null;
            try {
                uri = saveToSDCard(data); // 保存图片到sd卡中
                EventBus.getDefault().post(new PhotoEvent(uri));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
         * 将拍下来的照片存放在SD卡中
         *
         * @param data
         * @throws IOException
         */
        public Uri saveToSDCard(byte[] data) throws IOException {
            LogUtil.d("saveToSDCard", "saveToSDCard");

            //将得到的照片进行270°旋转，使其竖直
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            Matrix matrix = new Matrix();
            matrix.preRotate(90);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap
                    .getHeight(), matrix, true);


            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); // 格式化时间
            String filename = format.format(date) + ".jpg";
            File fileFolder = new File(Environment.getExternalStorageDirectory()
                    + "/himeetu/temp");
            if (!fileFolder.exists()) {
                fileFolder.mkdir();
            }
            File jpgFile = new File(fileFolder, filename);
            PhotoUtil.savePhoto2File(bitmap, jpgFile);
//            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "himeetu pic", "himeetu pic");

//            LogUtil.d(TAG, "MediaStore=" + path);
//            sendBroadcast(jpgFile);

            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return Uri.fromFile(jpgFile);
        }
    }

}
