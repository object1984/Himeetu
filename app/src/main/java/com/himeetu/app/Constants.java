package com.himeetu.app;

;

public class Constants {
    public static final String WEB_SERVER_ACCOUNT_DOMAIN = "www.himeetu.com:8888";
    public static final String WEB_SERVER_DOMAIN = "123.57.167.135";

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

    public static final int MODE_WORK_DAY = 1;
    public static final int MODE_OTHER_DAY = 2;
    public static final int MODE_BEFORE_DAY = 3;

    public static final String CONFIG_SERVICE_CALL_PHONE = "400-166-6969";
    public static final String CONFIG_SERVICE_CALL_NAME = "客服电话";

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
     * 支付方式配置文件
     */
    public static final String FILE_NAME_PAYTPYE_CONFIG = "paytype.json";

    public static final String FILE_NAME_CAR_SERVICE_CONFIG = "car_service.json";

    public static final String FILE_NAME_CANCEL_REASON_CONFIG = "cancel_reason.json";

    public static final String FILE_NAME_PHOTO_CATEGORY_CONFIG = "photo_category.json";
    /**
     * 崩溃日志文件名前缀
     */
    public static final String CRASH_FILE_NAME_PREFFIX = "eby-crash-";
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
    /**
     * jar更新类型
     */
    public static final int UPDATE_TYPE_JAR = 1;
    /**
     * apk更新类型
     */
    public static final int UPDAE_TYPE_APK = 2;
    /**
     * 一天时间毫秒
     */
    public static final long ONE_DAY_TIME_MILLIS = 86400000L;

    /**
     * 现金支付,CASH(1),
     */
    public static final int PAY_TYPE_CASH = 1;
    /**
     * 刷卡POS机支付
     * <p/>
     * CARD(2),
     */
    public static final int PAY_TYPE_CARD = 2;
    /**
     * 微信支付
     * <p/>
     * WEIXIN(3),
     */
    public static final int PAY_TYPE_WEIXIN = 3;
    /**
     * 钱包支付,WALLET(5);
     */
    public static final int PAY_TYPE_WALLET = 5;

}
