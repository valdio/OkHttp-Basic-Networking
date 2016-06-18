package com.example.valdio.okhttpbasicnetworking;

import java.io.File;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Valdio Veliu on 16-06-16.
 */
public class RequestBuilder {

    //Login request body
    public static RequestBody LoginBody(String username, String password, String token) {
        return new FormBody.Builder()
                .add("action", "login")
                .add("format", "json")
                .add("username", username)
                .add("password", password)
                .add("logintoken", token)
                .build();
    }

    public static HttpUrl buildURL() {
        return new HttpUrl.Builder()
                .scheme("https") //http
                .host("www.somehostname.com")
                .addPathSegment("pathSegment")//adds "/pathSegment" at the end of hostname
                .addQueryParameter("param1", "value1") //add query parameters to the URL
                .addEncodedQueryParameter("encodedName", "encodedValue")//add encoded query parameters to the URL
                .build();
        /**
         * The return URL:
         *  https://www.somehostname.com/pathSegment?param1=value1&encodedName=encodedValue
         */

    }

    // Picture of the day RSS feed
    public static HttpUrl pictureOfTheDay() {
        return new HttpUrl.Builder()
                .scheme("https")
                .host("commons.wikimedia.org")
                .addPathSegment("w")
                .addPathSegment("api.php")
                .addQueryParameter("action", "featuredfeed")
                .addQueryParameter("format", "xml")
                .addQueryParameter("feed", "potd")
                .build();
    }

    //Upload request body
    public static MultipartBody uploadRequestBody(String title, String imageFormat, String token, File file) {

        MediaType MEDIA_TYPE = MediaType.parse("image/" + imageFormat); // e.g. "image/png"
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("action", "upload")
                .addFormDataPart("format", "json")
                .addFormDataPart("filename", title + "." + imageFormat) //e.g. title.png --> imageFormat = png
                .addFormDataPart("file", "...", RequestBody.create(MEDIA_TYPE, file))
                .addFormDataPart("token", token)
                .build();
    }

}
