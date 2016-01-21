package com.himeetu.app;

;

public class Constants {
    public static final String WEB_SERVER_ACCOUNT_DOMAIN = "www.himeetu.com:8888";
    public static final String WEB_SERVER_DOMAIN = "www.himeetu.com";
    public static final String WEB_IMG_BASE = "http://www.himeetu.com/sysimg/";
    public static final String HEAD_IMG_BASE = "http://www.himeetu.com/img/";

    public static final String CONFIG_FILE_NAME = "config";
    public static final String CONFIG_TEMPLATE_INIT = "config_template_init";
    public static final String CONFIG_GET_VERSION_TIME = "config_get_version_time";
    public static final String CONFIG_APP_VERSION = "config_app_version";
    public static final String CONFIG_UPDATE_PAYTYPE_TIME = "update_paytype_time";
    public static final String CONFIG_UPDATE_PHOTO_CATEGORY_TIME = "update_photo_category_time";
    public static final String CONFIG_UPDATE_CANCEL_REASON_TIME = "update_cancel_reason_time";
    public static final String CONFIG_UPDATE_CAR_SERVICE_TIME = "update_car_service_time";


    public static final String CONFIG_TOKEN = "config_token";
    public static final String CONFIG_USER = "config_user";
    public static final String CONFIG_USER_IMG_URL = "config_user_img_url";
    public static final String CONFIG_USER_Head_URL = "config_user_head_url";
    public static final String CONFIG_CURRENT_ORDER_ID = "config_current_order_id";
    public static final String CONFIG_FOLDER = "/himeetu ";
    public static final String CONFIG_FOLDER_USER = "/user";
    public static final String CONFIG_FOLDER_ORDER = "order";

    public static final String CONFIG_PUSH_CHANNELE_ID = "";
    /**
     * 服务端接口响应码：成功
     */
    public static final int API_CODE_OK = 0;
    /**
     * 无效的View id
     */
    public static final int INVALID_VIEW_ID = 0;

    /**
     * 崩溃日志文件名前缀
     */
    public static final String CRASH_FILE_NAME_PREFFIX = "himeetu-crash-";
    /**
     * 查询订单支付状态：支付成功
     */
    public static final String API_QUERY_PAY_OK = "TRADE_SUCCESS";
    /**
     * 崩溃日志目录
     */
    public static final String FILE_DIR_CRASH = "/sdcard/crash/";
    /**
     * 崩溃日志批量提交文件个数
     */
    public static final int CRASH_UPLOAD_BATCH_FILE_NUM = 5;


}
