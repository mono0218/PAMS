package com.monodev.PAMS;

import android.util.Log;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import org.json.JSONObject;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class API {
    public static final MediaType JSON = MediaType.get("application/json");
    final OkHttpClient client = new OkHttpClient();
    String Post(String url, String text) throws IOException {
        String json = "{\"message\":\"+ " + text +"+\"}";

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONObject _json = new JSONObject(response.body().string());
            String result  = _json.getString("result");
            Log.i("test",result);
            return result;
        } catch (Exception e) {
            Log.e("Error","JSON解析でエラーが発生しました");
            FirebaseCrashlytics.getInstance().recordException(e);
            System.out.println(e);
            return "error";
        }
    }
}
