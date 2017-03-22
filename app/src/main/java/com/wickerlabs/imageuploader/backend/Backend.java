package com.wickerlabs.imageuploader.backend;

import android.graphics.Bitmap;

import com.wickerlabs.imageuploader.interfaces.UploadCallback;
import com.wickerlabs.imageuploader.util.Constants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
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
        // Write image bytes to a ByteArray
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, baos);
        byte[] imageBytes = baos.toByteArray();

        // Generate a random file name for the image
        String randomChunk = UUID.randomUUID().toString().substring(0, 8).replaceAll("-", "");
        String imageName = randomChunk.concat(".jpg");

        // Build a multipart body & request
        RequestBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("imageFile", imageName, RequestBody.create(MediaType.parse("image/*"), imageBytes))
                .build();

        Request request = new Request.Builder()
                .url(Constants.URL)
                .post(body)
                .build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Notify the UploadCallback about the upload error
                callback.OnUploadComplete(null, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Notify the UploadCallback about the upload completion
                callback.OnUploadComplete(response.body().string(), null);
            }
        });
    }
}
