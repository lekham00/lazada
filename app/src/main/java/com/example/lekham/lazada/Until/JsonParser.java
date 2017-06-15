package com.example.lekham.lazada.Until;


import com.example.lekham.lazada.Model.ObjectClass.ThuongHieu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Le Kham on 6/9/2017.
 */

public class JsonParser {
    private static JsonSerializer<Date> dateJsonSerializer = new JsonSerializer<Date>() {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            return src == null ? null : new JsonPrimitive(TimeUnit.SECONDS.toMillis(src.getTime()));
        }
    };
    private static JsonDeserializer<Date> dateJsonDeserializer = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : new Date(TimeUnit.SECONDS.toMillis(json.getAsLong()));
        }
    };

    public static JsonElement toJsonElement(Object object) {
        return new Gson().toJsonTree(object);
    }

    public static <Object> Object fromJson(String json, Class<Object> objectClass) throws Exception {
        JSONObject jsonObject = (JSONObject) new JSONTokener(json).nextValue();
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, dateJsonSerializer)
                .registerTypeAdapter(Date.class, dateJsonDeserializer)
                .create();
        return jsonObject != null ? gson.fromJson(json, objectClass) : null;
    }

    public static <S> List<S> parseStringToList(String strJson, Class<S[]> clazz) {
        S[] arr = new Gson().fromJson(strJson, clazz);
        return Arrays.asList(arr);
    }
}
