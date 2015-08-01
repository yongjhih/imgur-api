package com.akiniyalocts.imgur.api;

import android.support.annotation.NonNull;

import com.akiniyalocts.imgur.api.model.Album;
import com.akiniyalocts.imgur.api.model.Image;
import com.akiniyalocts.imgur.api.model.Response;
import com.akiniyalocts.imgur.api.model.post.AlbumResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Retrofit;
import retrofit.http.Retrofit.Headers;
import retrofit.http.Retrofit.LogLevel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Implementations for the Imgur REST interface.
 * Provides instance, setup and calls to the ImgurApi
 *
 * @see com.akiniyalocts.imgur.api.ImgurAPI
 */
@Retrofit(Constants.API_IMGUR_URL)
@LogLevel(RestAdapter.LogLevel.FULL)
@Headers({
    "Authorization: CLIENT-ID 3efbb6d75f6524f",
    "Accept: application/json"
})
public abstract class ImgurClient implements ImgurAPI {

    private static ImgurClient instance;

    /**
     * To make requests for the current account, you may use me as the {username} parameter.
     * For example, https://api.imgur.com/3/account/me/images will request all the images
     * for the account that is currently authenticated.
     *
     * @see https://api.imgur.com/endpoints/account#current
     */
    public void getImages(Callback<List<Image>> cb) {
        getImages("me", cb);
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Updates an album
     *
     * @param album album
     * @param cb    callback
     */
    public void updateAlbum(@NonNull Album album, @NonNull Callback<Response> cb) {
        com.akiniyalocts.imgur.api.model.post.Album postAlbum =
                new com.akiniyalocts.imgur.api.model.post.Album(album);
        updateAlbum(postAlbum, album.getId(), album.getDeletehash(), cb);
    }

    /**
     * Updates an album, provide either deletehash or album id
     *
     * @param album      album
     * @param albumId    id of album
     * @param deleteHash deletehash of album
     * @param cb         callback
     */
    public void updateAlbum(@NonNull com.akiniyalocts.imgur.api.model.post.Album album,
                            String albumId,
                            String deleteHash,
                            @NonNull Callback<Response> cb) {

        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNotNullOrEmpty(albumId)) {
            updateAlbum(albumId, album, cb);
        } else {
            updateAlbum(deleteHash, album, cb);
        }
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Deletes an album, provide on of both ids
     *
     * @param albumId    album id
     * @param deleteHash deletehash
     * @param cb         callback
     */
    public void deleteAlbum(String albumId, String deleteHash, @NonNull Callback<Response> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");
        if (Util.isNotNullOrEmpty(deleteHash)) {
            deleteAlbum(deleteHash, cb);
        } else {
            deleteAlbum(albumId, cb);
        }
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Sets images for an album
     *
     * @param album    album object
     * @param imageIds imageIds
     * @param cb       callback
     */
    public void setAlbumImages(@NonNull Album album,
                               @NonNull String[] imageIds,
                               @NonNull Callback<Response> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        String deleteHash = album.getDeletehash();
        if (deleteHash.isEmpty()) {
            setAlbumImages(album.getId(), imageIds, cb);
        } else {
            setAlbumImages(album.getDeletehash(), imageIds, cb);
        }
    }
    /**
     * Sets images for an album
     * Provide either albumId or deleteHash
     *
     * @param albumId    album id
     * @param deleteHash deletehash for album
     * @param imageIds   imageIds
     * @param cb         callback
     */
    public void setAlbumImages(String albumId,
                               String deleteHash,
                               @NonNull String[] imageIds,
                               @NonNull Callback<Response> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");

        if (Util.isNotNullOrEmpty(deleteHash)) {
            setAlbumImages(deleteHash, imageIds, cb);
        } else {
            setAlbumImages(albumId, imageIds, cb);
        }
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Sets images for an album
     *
     * @param album album object which includes imageIds
     * @param cb    callback
     */
    public void setAlbumImages(@NonNull Album album, @NonNull Callback<Response> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        String deleteHash = album.getDeletehash();
        if (deleteHash.isEmpty()) {
            setAlbumImages(album.getId(), album.getImageIds(), cb);
        } else {
            setAlbumImages(album.getDeletehash(), album.getImageIds(), cb);
        }
    }
    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Add images to album
     *
     * @param album    album object
     * @param imageIds image ids which will be added to the album
     * @param cb       callback
     */
    public void addImagesToAlbum(@NonNull Album album,
                                 @NonNull String[] imageIds,
                                 @NonNull Callback<Response> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        String deleteHash = album.getDeletehash();
        if (deleteHash.isEmpty()) {
            addImagesToAlbum(album.getId(), imageIds, cb);
        } else {
            addImagesToAlbum(album.getDeletehash(), imageIds, cb);
        }
    }

    /**
     * Add images to an album
     * Provide either albumId or deleteHash
     *
     * @param albumId    album id
     * @param deleteHash deletehash for album
     * @param imageIds   imageIds
     * @param cb         callback
     */
    public void addImagesToAlbum(String albumId,
                                 String deleteHash,
                                 @NonNull String[] imageIds,
                                 @NonNull Callback<Response> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");

        if (Util.isNotNullOrEmpty(deleteHash)) {
        } else {
            addImagesToAlbum(albumId, imageIds, cb);
        }
    }

    //// TODO: 29.07.15 Remove duplicate code checking for deletehash

    /**
     * Add images to album
     *
     * @param album    album object
     * @param imageIds image ids which will be removed from the album
     * @param cb       callback
     */
    public void deleteImagesFromAlbum(@NonNull Album album,
                                      @NonNull String[] imageIds,
                                      @NonNull Callback<Response> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        String deleteHash = album.getDeletehash();
        if (deleteHash.isEmpty()) {
            deleteImagesFromAlbum(album.getId(), imageIds, cb);
        } else {
            deleteImagesFromAlbum(album.getDeletehash(), imageIds, cb);
        }
    }

    /**
     * Deletes images from an album
     * Provide either albumId or deleteHash
     *
     * @param albumId    album id
     * @param deleteHash deletehash for album
     * @param imageIds   imageIds
     * @param cb         callback
     */
    public void deleteImagesFromAlbum(String albumId,
                                      String deleteHash,
                                      @NonNull String[] imageIds,
                                      @NonNull Callback<Response> cb) {
        //anonymously created albums have a deletehash, which can be used
        //to update and delete an album
        if (Util.isNullOrEmpty(albumId) && Util.isNullOrEmpty(deleteHash))
            throw new IllegalArgumentException("AlbumId or Deletehash must be supplied");

        if (Util.isNotNullOrEmpty(deleteHash)) {
            deleteImagesFromAlbum(deleteHash, imageIds, cb);
        } else {
            deleteImagesFromAlbum(albumId, imageIds, cb);
        }
    }

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
