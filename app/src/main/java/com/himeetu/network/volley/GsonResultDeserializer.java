package com.himeetu.network.volley;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.himeetu.model.GsonResult;

import java.lang.reflect.Type;

/**
 * Deserializer for a dummy object
 *
 * Convert a JsonObject into a Dummy object.
 */
public class GsonResultDeserializer implements JsonDeserializer<GsonResult>
{
    @Override
    public GsonResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException
    {


        final GsonResult gsonResult = new GsonResult();
        final JsonObject jsonObject = json.getAsJsonObject();

//        gsonResult.setCode(jsonObject.get("code").getAsInt());
//        gsonResult.setMsg(jsonObject.get("msg").getAsString());
//
//
        Gson gson = new GsonBuilder().create();
        gsonResult.setSuccess(jsonObject.get("success").getAsBoolean());

        JsonElement questionJsonObj = jsonObject.get("questionDetail");
//        gsonResult.setData(gson.fromJson(questionJsonObj, Question.class));
        return gsonResult;
    }
}
