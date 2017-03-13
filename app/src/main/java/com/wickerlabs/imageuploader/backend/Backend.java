package com.wickerlabs.imageuploader.backend;

import android.graphics.Bitmap;

import com.wickerlabs.imageuploader.interfaces.UploadCallback;
import com.wickerlabs.imageuploader.util.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by: WickerLabs.Inc
 * Project title: ImageUploader
 * Time: 1:11 PM
 * Date: 3/13/2017
 * Website: http://www.wickerlabs.com
 */

public class Backend {
    private static final Backend ourInstance = new Backend();
    private OkHttpClient httpClient;

    private Backend() {
        httpClient = new OkHttpClient();
    }

    public static Backend getInstance() {
        return ourInstance;
    }

    public void uploadImageFile(Bitmap bitmap, final UploadCallback callback) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, baos);
        String imageName = FileUtils.toFileSystemSafeName(UUID.randomUUID().toString().substring(0, 8).replaceAll("-", "").concat(".jpg"));

        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("imageFile", imageName, RequestBody.create(MediaType.parse("image/*"), baos.toByteArray()))
                .build();

        final Request request = new Request.Builder()
                .url("http://flashesvs.com/backend/file_upload/upload.php")
                .post(body)
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = httpClient.newCall(request).execute();

                    if (response.isSuccessful()) {
                        callback.OnUploadComplete(response.body().string(), null);
                    } else {
                        callback.OnUploadComplete(null, new IOException("Error in getting response"));
                    }
                } catch (Exception e) {
                    callback.OnUploadComplete(null, e);
                }
            }
        }).start();
    }
}
