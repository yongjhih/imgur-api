package com.akiniyalocts.imgur.api.app;

import android.app.Application;

import com.akiniyalocts.imgur.api.ImgurClient;

/**
 * Created by anthony on 7/27/15.
 */
public class ImgurTestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createImgurClient();
    }

    private void createImgurClient(){
        ImgurClient.getInstance();
    }
}
