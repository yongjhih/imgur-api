package com.akiniyalocts.imgur.api;

import android.support.annotation.NonNull;

import com.akiniyalocts.imgur.api.model.Album;
import com.akiniyalocts.imgur.api.model.Image;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Retrofit;
import retrofit.http.Retrofit.Headers;
import retrofit.http.Retrofit.LogLevel;

/**
 * Implementations for the Imgur REST interface.
 * Provides instance, setup and calls to the ImgurApi
 *
 * @see com.akiniyalocts.imgur.api.ImgurAPI
 */
@Retrofit(Constants.API_BASE_URL)
@LogLevel(RestAdapter.LogLevel.FULL)
@Headers(Constants.AUTH_CLIENT_ID)
public abstract class ImgurClient implements ImgurAPI {

    private static ImgurClient instance;

    /**
     * Use to obtain instance of ImgurClient
     * Instance will be created if it was not created previously
     */
    public static ImgurClient getInstance() {
        if (instance == null) {
            instance = create();
        }
        return instance;
    }

    public static ImgurClient create() {
        return new Retrofit_ImgurClient();
    }
}
