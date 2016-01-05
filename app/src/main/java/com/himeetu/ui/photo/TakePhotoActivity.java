package com.himeetu.ui.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.himeetu.R;
import com.himeetu.app.NavHelper;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.util.LogUtil;
import com.himeetu.util.ToastUtil;
import com.himeetu.view.HiSurfaceView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by object1984 on 15/12/21.
 */
public class TakePhotoActivity extends BaseActivity implements View.OnClickListener, Handler.Callback {
    private static final String TAG = "TakePhotoActivity";
    private Camera camera;
    private Camera.Parameters parameters = null;
    private Button cancelButton;
    private Button pictureButton;
    private Handler handler;
    private boolean hasBitmap = false;
    private Uri uri;
    private static final int SURFAVCE_WIDTH = 720;
    private static final int SURFAVCE_HEIGHT = 720;
    private static final int PICTURE_WIDTH = 720;
    private static final int PICTURE_HEIGHT = 720;
    private boolean enable = true;
    private HiSurfaceView hiSurfaceView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeTranslucent();
        setContentView(R.layout.activity_photo_take);
        setupToolbar(true, R.string.photo_take);
        init();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (camera == null){
            camera = getCameraInstance();
            }
        //必须放在onResume中，不然会出现Home键之后，再回到该APP，黑屏
        hiSurfaceView = new HiSurfaceView(getApplicationContext(), camera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(hiSurfaceView);
    }

    /*得到一相机对象*/
    private Camera getCameraInstance(){
        Camera camera = null;
        try{
            camera = camera.open();
        }catch(Exception e){
            e.printStackTrace();
        }
        return camera;
    }

    @Override
    protected void setupListeners() {
        super.setupListeners();
        findViewById(R.id.btn_photo_take).setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    // 拍照
    private void takePicture() {
        if (!hasBitmap) {
//            pictureButton.setOnClickListener(null);
            camera.takePicture(null, null, new MyPictureCallback());
        } else {
            Intent resultIntent = new Intent();
            resultIntent.setData(uri);
            setResult(RESULT_OK, resultIntent);
            finish();
        }

    }

    private void cancel() {
        if (hasBitmap) {

            if (camera != null) {
                camera.stopPreview();
                camera.startPreview();
            }

            hasBitmap = false;
//            pictureButton.setText("拍 照");
        } else {
            finish();
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        ToastUtil.show("拍照成功");

        uri = (Uri) msg.getData().get("uri");
        hasBitmap = true;
//        pictureButton.setText("完 成");
//        pictureButton.setOnClickListener(this);
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_photo_take:
                if (camera != null) {
                    takePicture();

                    NavHelper.toPhotoTakeResultPage(this);
                }
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

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
            } catch (IOException e) {
                e.printStackTrace();
            }

            Message msg = new Message();
            Bundle data = new Bundle();
            data.putParcelable("uri", uri);
            msg.setData(data);
            handler.sendMessage(msg);
        }

        /**
         * 将拍下来的照片存放在SD卡中
         *
         * @param data
         * @throws IOException
         */
        public Uri saveToSDCard(byte[] data) throws IOException {
            LogUtil.d(TAG, "saveToSDCard");

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
                    + "/eby/temp");
            if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                fileFolder.mkdir();
            }
            File jpgFile = new File(fileFolder, filename);
//            PhotoUtil.savePhoto2File(bitmap, jpgFile);

            String path = MediaStore.Images.Media.insertImage(getContentResolver(), jpgFile.getAbsolutePath(), filename, "eby pic");

            LogUtil.d(TAG, "MediaStore=" + path);
            sendBroadcast(jpgFile);

            if (!bitmap.isRecycled()) {
                bitmap.recycle();
            }
            return Uri.fromFile(jpgFile);
        }
    }

    private void sendBroadcast(File file) {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }

    private final class MyPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            try {
//                pictureButton.setText("处理中...");
                new SaveBitmapThread(data).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ;


    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_CAMERA: // 按下拍照按钮
                if (camera != null && event.getRepeatCount() == 0) {
                    // 拍照
                    //注：调用takePicture()方法进行拍照是传入了一个PictureCallback对象——当程序获取了拍照所得的图片数据之后
                    //，PictureCallback对象将会被回调，该对象可以负责对相片进行保存或传入网络
                    camera.takePicture(null, null, new MyPictureCallback());
                }
        }
        return super.onKeyDown(keyCode, event);
    }


}
