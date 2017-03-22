package com.wickerlabs.imageuploader.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by: WickerLabs.Inc
 * Project title: Image-Uploader
 * Time: 12:19 AM
 * Date: 2/13/2017
 * Website: http://www.wickerlabs.com
 */

public class DialogUtils {

    private static ProgressDialog dialog;
    private static DialogUtils instance;

    private DialogUtils() {

    }

    public static void init() {
        instance = new DialogUtils();
    }

    public static DialogUtils getInstance() {
        return instance;
    }

    public void startProgress(Context context, String message) {
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
    }

    public void stopProgress() {
        dialog.dismiss();
    }
}
