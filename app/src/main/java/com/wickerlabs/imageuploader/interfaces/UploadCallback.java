package com.wickerlabs.imageuploader.interfaces;

/**
 * Created by: WickerLabs.Inc
 * Project title: ImageUploader
 * Time: 1:10 PM
 * Date: 3/13/2017
 * Website: http://www.wickerlabs.com
 */

public interface UploadCallback {
    void OnUploadComplete(String response, Exception e);
}
