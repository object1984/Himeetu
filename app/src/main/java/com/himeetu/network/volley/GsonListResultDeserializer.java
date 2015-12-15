package com.himeetu.network.volley;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.himeetu.model.GsonListResult;

import java.lang.reflect.Type;

/**
 * Deserializer for a dummy object
 *
 * Convert a JsonObject into a Dummy object.
 */
public class GsonListResultDeserializer implements JsonDeserializer<GsonListResult>
{
    @Override
    public GsonListResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException
    {


        final GsonListResult gsonListResult = new GsonListResult();
        final JsonObject jsonObject = json.getAsJsonObject();

        gsonListResult.setPageSize(jsonObject.get("pageSize").getAsInt());
        gsonListResult.setStart(jsonObject.get("start").getAsInt());
        gsonListResult.setTotalCount(jsonObject.get("totalCount").getAsInt());

        JsonArray jsonArray = jsonObject.getAsJsonArray("data");

//        Type listType = new TypeToken<List<PushMessage>>(){}.getType();
//        List<PushMessage> list = new Gson().fromJson(jsonArray, listType);
//
//        gsonListResult.setDataList(list == null ? new ArrayList<PushMessage>() : list);
        return gsonListResult;
    }
}
