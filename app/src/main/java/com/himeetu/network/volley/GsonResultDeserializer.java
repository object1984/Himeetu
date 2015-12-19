package com.himeetu.network.volley;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.himeetu.model.GsonResult;
import com.himeetu.util.JsonUtil;

import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Deserializer for a dummy object
 *
 * Convert a JsonObject into a Dummy object.
 */
public class GsonResultDeserializer implements JsonDeserializer<GsonResult> {
    @Override
    public GsonResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        GsonResult gsonResult = new GsonResult();

        JSONObject jsonObject = JsonUtil.getJSONObject(json.toString());
        gsonResult.setCode(JsonUtil.getInt(jsonObject, "result"));

        return gsonResult;
    }
}
