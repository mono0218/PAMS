package com.monodev.PAMS;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.monodev.pams.R;

import java.io.IOException;
import java.util.Arrays;
    public class POST extends AsyncTask<String, String, String> {

        private final Context context;
        public NotificationManager NotifyManager;
        public NotificationCompat.Builder build;

        public POST (Context context) {
            this.context = context;
        }

        final int id = 101;
        @Override
        protected String doInBackground(String... text) {
            API _post = new API();
            try {
                String result = _post.Post("https://oyster-app-gzdib.ondigitalocean.app/post/", "" + Arrays.toString(text) + "");
                Log.i("test",result);
                return result;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result.equals("spam")){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"default");
                builder.setSmallIcon(R.drawable.ic_launcher_foreground);
                builder.setContentTitle("PAMS");
                builder.setContentText("フィッシングメッセージを検知しました。\n注意してください.");
                builder.setPriority(NotificationCompat.PRIORITY_MAX);
                builder.setCategory(NotificationCompat.CATEGORY_MESSAGE);
                builder.setChannelId("default");

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
                notificationManager.notify(1111, builder.build());
            }
        }
    }
