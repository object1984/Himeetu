package com.himeetu.network.volley;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.himeetu.model.GsonResult;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Deserializer for a dummy object
 *
 * Convert a JsonObject into a Dummy object.
 */
public class CourseQuestionDeserializer implements JsonDeserializer<GsonResult>
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
//        gsonResult.setSuccess(jsonObject.get("success").getAsBoolean());

//        Type listType = new TypeToken<List<QuestionIndex>>(){}.getType();
//        List<QuestionIndex> list = new Gson().fromJson(jsonObject.getAsJsonArray("questionIndexList"), listType);
//        gsonResult.setData(list);
        return gsonResult;
    }
}
