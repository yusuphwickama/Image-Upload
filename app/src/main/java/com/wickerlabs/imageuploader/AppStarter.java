package com.wickerlabs.imageuploader;

import android.app.Application;

import com.wickerlabs.imageuploader.util.DialogUtils;


/**
 * Created by: WickerLabs.Inc
 * Project title: ImageUploader
 * Time: 7:44 PM
 * Date: 3/13/2017
 * Website: http://www.wickerlabs.com
 */

public class AppStarter extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        DialogUtils.init();
    }
}
