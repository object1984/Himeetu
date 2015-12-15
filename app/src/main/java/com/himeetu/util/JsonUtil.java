package com.himeetu.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class JsonUtil {
    private static final String TAG = "JsonUtil";
    public static Gson gson = new Gson();

    public static double getDouble(JSONObject json, String name) {
        try {
            if (json != null && json.has(name)) {
                return json.getDouble(name);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public static int getInt(JSONObject json, String name) {
        return getInt(json, name, 0);
    }

    public static int getInt(JSONObject json, String name, int def) {
        try {
            if (json != null && json.has(name)) {
                return json.getInt(name);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return def;
    }

    public static boolean getBoolean(JSONObject json, String name) {
        try {
            if (json != null && json.has(name)) {
                return json.getBoolean(name);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static boolean put(JSONObject json, String key, Object value) {
        try {
            if (json != null) {
                json.put(key, value);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    public static String getString(JSONObject json, String name) {
        try {
            if (json != null && json.has(name)) {
                return json.getString(name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getLong(JSONObject json, String name) {
        try {
            if (json != null && json.has(name)) {
                return json.getLong(name);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    public static String getString(JSONArray jsonAry, int index) {
        try {
            if (jsonAry != null && jsonAry.length() > index) {
                return jsonAry.getString(index);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDate(JSONObject json, String name) {
        Date d = null;
        try {
            if (json != null && json.has(name)) {
                String str = json.getString(name);
                d = DateUtils.parse(str);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    public static JSONObject getJSONObject(JSONArray jAry, int index) {
        try {
            if (jAry != null && jAry.length() > index) {
                return jAry.getJSONObject(index);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJSONObject(JSONObject json, String name) {
        try {
            if (json != null && json.has(name)) {
                return json.getJSONObject(name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJSONObject(String value) {
        try {
            if (value != null && !"".equals(value) && !"null".equals(value)) {
                return new JSONObject(value);
            }
        } catch (JSONException e) {
            LogUtil.d("getJSONObject", String.format("value=%s", value));
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray getJSONArray(String value) {
        try {
            if (value != null && !"".equals(value))
                return new JSONArray(value);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static JSONArray getJSONArray(JSONObject json, String name) {
        try {
            if (json != null) {
                return json.getJSONArray(name);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String beanToJson(Object obj) {
        String json = "";
        if (obj != null) {
            json = gson.toJson(obj);
        }
        return json;
    }


//    public static Object jsonToBean(String json, Class<?> clazz) {
//        try {
//            return gson.fromJson(json, clazz);
//        } catch (Exception e) {
//            LogUtil.loge("JsonUtil", "jsonToBean error: " + json, e);
//            e.printStackTrace();
//            return null;
//        }
//    }

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            LogUtil.loge("JsonUtil", "jsonToBean error: " + json, e);
            e.printStackTrace();
            return null;
        }
    }

    public static List parseArray(String json, Class clazz) {
        JSONArray jsonArray = getJSONArray(json);
        List list = new ArrayList<Object>();
        for (int i = 0; i < jsonArray.length(); i++) {
            list.add(jsonToBean(JsonUtil.getJSONObject(jsonArray, i).toString(), clazz));
        }

        return list;
    }

    /**
     * json字符串转化为List对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToBeanList(String json, Class<T[]> clazz) {

        try {
            T[] arr = gson.fromJson(json, clazz);
            if (arr != null) {
                return Arrays.asList(arr);
            }
        } catch (JsonSyntaxException e) {
            LogUtil.loge(TAG, StringUtil.join("jsonToBeanList error: json==", json), e);
        }

        return null;
    }
}
