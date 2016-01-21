package com.himeetu.ui.setup;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.himeetu.BuildConfig;
import com.himeetu.R;
import com.himeetu.app.Api;
import com.himeetu.app.Constants;
import com.himeetu.event.UserInfoRefreshEvent;
import com.himeetu.model.GsonResult;
import com.himeetu.model.SelectData;
import com.himeetu.model.User;
import com.himeetu.model.service.UserService;
import com.himeetu.network.dic.Argument;
import com.himeetu.network.dic.UrlPatten;
import com.himeetu.ui.base.BaseActivity;
import com.himeetu.ui.base.BaseVolleyActivity;
import com.himeetu.util.FileUtil;
import com.himeetu.util.RoundedTransformation;
import com.himeetu.util.ToastUtil;
import com.himeetu.view.SelectPicPopupWindow;

import android.os.Bundle;
import android.app.Activity;
import android.widget.RelativeLayout;

import com.github.siyamed.shapeimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.greenrobot.event.EventBus;

/**
 * 编辑用户资料   id   email  不能修改
 */
public class EditUserDetailActivity extends BaseVolleyActivity implements View.OnClickListener {
    private SelectPicPopupWindow menuWindow;
    private RelativeLayout rlUserHead;
    private RoundedImageView rivUserHead, riv_user_head_edit;
    private RelativeLayout rlName;
    private TextView tvName;
    private RelativeLayout rlPhone;
    private TextView tvPhone;
    private RelativeLayout rlSex;
    private TextView tvSex;
    private RelativeLayout rlBirthday;
    private TextView tvBirthday;
    private TextView tvId;
    private TextView tvEmail;
    private static final String TAG_API_GET_SELF_INFO = "TAG_API_GET_SELF_INFO";
    //    private RelativeLayout rlQuestion;
//    private TextView tvQuestion;
//    private TextView tvAnswer;
    private LinearLayout llShow, llEdit;
    private EditText et_name, et_email, et_phone, et_sex, et_birthday, et_id;
    //    private EditText et_question,et_answer;
    private TextView tv_head_edit;
    private static final String TAG_UPDATE_DATA_DETAIL = "TAG_UPDATE_DATA_DETAIL";
    private final static int GET_FROM_CAMERA = 1;
    private final static int GET_FROM_ALBUM = 2;
    private final static int REQUEST_CROP_IMAGE = 3;
    private File file;
    private final static String TAG_UPLOAD_PIC = "TAG_UPLOAD_PIC";
    private String uploadPath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_detail);
        super.init();
        initToolBar();

    }


    @Override
    protected void loadViews() {
        super.loadViews();

        rlUserHead = (RelativeLayout) findViewById(R.id.rl_user_head);
        rivUserHead = (RoundedImageView) findViewById(R.id.riv_user_head);
        rlName = (RelativeLayout) findViewById(R.id.rl_name);
        tvName = (TextView) findViewById(R.id.tv_name);
        rlPhone = (RelativeLayout) findViewById(R.id.rl_phone);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        rlSex = (RelativeLayout) findViewById(R.id.rl_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        tvId = (TextView) findViewById(R.id.tv_id);
        tvEmail = (TextView) findViewById(R.id.tv_email);
        rlBirthday = (RelativeLayout) findViewById(R.id.rl_birthday);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
//        rlQuestion = (RelativeLayout) findViewById(R.id.rl_question);
//        tvQuestion = (TextView) findViewById(R.id.tv_question);
//        tvAnswer = (TextView) findViewById(R.id.tv_answer);
        riv_user_head_edit = (RoundedImageView) findViewById(R.id.riv_user_head_edit);
        llShow = (LinearLayout) findViewById(R.id.ll_show);
        llEdit = (LinearLayout) findViewById(R.id.ll_edit);
        tv_head_edit = (TextView) findViewById(R.id.tv_head_edit);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_sex = (EditText) findViewById(R.id.et_sex);
        et_birthday = (EditText) findViewById(R.id.et_birthday);
//        et_question = (EditText) findViewById(R.id.et_question);
//        et_answer = (EditText) findViewById(R.id.et_answer);
        et_id = (EditText) findViewById(R.id.et_id);

    }

    @Override
    protected void initViews() {
        super.initViews();

        User user = UserService.get();

        et_id.setText(user.getUid() + "");
        et_name.setText(user.getNickname());
        et_birthday.setText(user.getBirthday());
        et_sex.setText("1".equals(user.getSex()) ? "女" : "男");
        et_email.setText(user.getEmail());
        et_phone.setText(user.getTelphone());
        Picasso.with(this).load(Constants.HEAD_IMG_BASE+user.getPortrait()).placeholder(R.drawable.img_avatar_default)
                .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(riv_user_head_edit);

    }

    @Override
    protected void setupListeners() {
        super.setupListeners();

//        rlUserHead.setOnClickListener(this);
//        rlName.setOnClickListener(this);
//        rlPhone.setOnClickListener(this);
//        rlSex.setOnClickListener(this);
//        rlBirthday.setOnClickListener(this);
//        rlQuestion.setOnClickListener(this);
        tv_head_edit.setOnClickListener(this);
        riv_user_head_edit.setOnClickListener(this);


    }

    private void initToolBar() {
        setupToolbar(true, R.string.edit_user_data);
        setRightTextAndVisible(R.string.complete, View.VISIBLE);

        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = getRightText();

                if (getString(R.string.edit).equals(text)) {//完成

                    setRightTextAndVisible(R.string.complete, View.VISIBLE);
                    llShow.setVisibility(View.GONE);
                    llEdit.setVisibility(View.VISIBLE);
                } else {//编辑
                    setRightTextAndVisible(R.string.edit, View.VISIBLE);
                    llShow.setVisibility(View.VISIBLE);
                    llEdit.setVisibility(View.GONE);

                    buildData();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

//            case R.id.rl_user_head:
//
//                break;
//
//            case R.id.rl_name:
//
//                break;
//            case R.id.rl_phone:
//
//                break;
//            case R.id.rl_sex:
//
//                break;
//            case R.id.rl_birthday:
//
//                break;
//            case R.id.rl_question:
//
//                break;


            case R.id.riv_user_head_edit:
                showSelectHeadView();
                break;

            case R.id.tv_head_edit:
                showSelectHeadView();

                break;

        }

    }


    /**
     * 显示pop
     */
    private void showSelectHeadView() {
        List<SelectData> datas = new ArrayList<>();
        SelectData data = new SelectData();
        data.setName(R.string.take_photos);
        data.setTextColor(R.color.text_dark_black);
        datas.add(data);

        data = new SelectData();
        data.setName(R.string.from_the_album_to_choose);
        data.setTextColor(R.color.text_dark_black);
        datas.add(data);

        menuWindow = new SelectPicPopupWindow(EditUserDetailActivity.this, new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(intent, GET_FROM_CAMERA);

                } else {
                    Intent i = new Intent(
                            Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(i, GET_FROM_ALBUM);
                }
                menuWindow.dismiss();
            }
        }, datas);

        //显示窗口
        menuWindow.showAtLocation(EditUserDetailActivity.this.findViewById(R.id.tv_head_edit), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == GET_FROM_CAMERA) {

                String name = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date(System.currentTimeMillis())) + ".jpg";
                Bundle bundle = data.getExtras();
                Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

                FileOutputStream b = null;

                File folder = new File(Environment.getExternalStorageDirectory()
                        + "/himeetu/temp");
                if (!folder.exists()) {
                    folder.mkdirs();
                }

                file = new File(folder, name);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    b = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, b);// 把数据写入文件

                    cropImage(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (b != null) {
                            b.flush();
                            b.close();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (requestCode == GET_FROM_ALBUM) {  //相册

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                file = new File(picturePath);
                cropImage(file);

            } else {

                // 拿到剪切数据
                Bitmap bmap = data.getParcelableExtra("data");
                // 图像保存到文件中
                FileOutputStream foutput = null;
                try {
                    foutput = new FileOutputStream(file);
                    bmap.compress(Bitmap.CompressFormat.PNG, 100, foutput);

                    Picasso.with(this).load(file).placeholder(R.drawable.img_avatar_default)
                            .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(riv_user_head_edit);

//                    UploadPic(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    if (null != foutput) {
                        try {
                            foutput.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 调用图片裁剪
     */
    private void cropImage(File file) {
        Intent intent = new Intent();
        intent.setAction("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(file), "image/*");// mUri是已经选择的图片Uri
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("output", Uri.parse("file:/" + file.getAbsolutePath()));
        startActivityForResult(intent, REQUEST_CROP_IMAGE);

    }

    private void buildData() {

//        if (isNull(et_name) || isNull(et_phone) || isNull(et_sex) || isNull(et_birthday)) {
//                || isNull(et_question) || isNull(et_answer)){

//            ToastUtil.show("资料填写不合格");
//            return;
//        }

        refreshView();

        String sex = "男".equals(et_sex.getText().toString()) ? "0" : "1";

        commit(UserService.get().getCountryCode() + "", sex, et_birthday.getText().toString(), et_phone.getText().toString(), et_email.getText().toString());

        if(file != null){
            UploadPic(file);
        }

    }

    private void refreshView() {

        tvName.setText(et_name.getText());
        tvPhone.setText(et_phone.getText());
        tvSex.setText("1".equals(et_sex.getText()) ? "女" : "男");
        tvBirthday.setText(et_birthday.getText());
        tvId.setText(et_id.getText());
        tvEmail.setText(et_email.getText());

    }

    private boolean isNull(EditText et) {
        if (et == null || isNull(et.getText().toString())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isNull(String str) {
        if (str == null || str.trim().length() < 1) {
            return true;
        } else {
            return false;
        }
    }

    private void commit(String nation, String sex, String birth, String phone, String email) {

        Api.updateUserDataDetail(TAG_UPDATE_DATA_DETAIL, nation, sex, birth, phone, email, this, this);

    }

    private void getSelfInfo() {
        Api.getSelfInfo(TAG_API_GET_SELF_INFO, this, this);
    }

    @Override
    public void onResponse(GsonResult response, String tag) {
        super.onResponse(response, tag);
        if (BuildConfig.DEBUG) Log.d("EditUserDetailActivity", response.getJsonStr());
        if (TAG_UPDATE_DATA_DETAIL.equals(tag)) {

            if (response.getCode() == 0) {

                ToastUtil.show(R.string.success);

                getSelfInfo();

            } else {

                ToastUtil.show(response.getMsg());
            }


        } else if (TAG_API_GET_SELF_INFO.equals(tag)) {

            User user = new Gson().fromJson(response.getJsonStr(), User.class);

            if (user != null) {
                user.setUsername(getIntent().getStringExtra(Argument.USERNAME));
            }

            UserService.save(user);

            Picasso.with(this).load(Constants.HEAD_IMG_BASE+user.getPortrait()).placeholder(R.drawable.img_avatar_default)
                    .error(R.drawable.img_avatar_default).transform(new RoundedTransformation(100, 0)).fit().into(rivUserHead);

        }
    }

    private void UploadPic(final File file) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = FileUtil.run(UrlPatten.URL_UPLOAD_STATE, "image/jpeg", file, "abc.jpg");

                    JSONObject obj = new JSONObject(result);
                    uploadPath = obj.getString("msg");
                    Api.uploadPic(TAG_UPLOAD_PIC, uploadPath, EditUserDetailActivity.this, EditUserDetailActivity.this);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    @Override
    public void onErrorResponse(VolleyError error, String tag) {
        super.onErrorResponse(error, tag);
    }
}
