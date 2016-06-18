package com.example.valdio.okhttpbasicnetworking;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private String response;
    private OkHttpClient client;

    String url = "https://www.somehostname.com/request-params";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Standard OkHttpClient
        client = new OkHttpClient();
        //OkHttpClient with cookieJar policy
        client = new OkHttpClient.Builder()
                .cookieJar(new CookieStore())
                .build();


        /*******************************************************************************/
//        //Upload request // Call asynchronously
//        File file = new File("");
//        try {
//            ApiCall.POST(client, url, RequestBuilder.uploadRequestBody("title", "png", "someUploadToken", file));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        /*******************************************************************************/
//        //Monitored request // Call asynchronously
//        File file = new File("");
//        MultipartBody body = RequestBuilder.uploadRequestBody("title", "png", "someUploadToken", file);
//
//        CountingRequestBody monitoredRequest = new CountingRequestBody(body, new CountingRequestBody.Listener() {
//            @Override
//            public void onRequestProgress(long bytesWritten, long contentLength) {
//                //Update a  progress bar with the following percentage
//                float percentage = 100f * bytesWritten / contentLength;
//                if (percentage >= 0) {
//                    //TODO: Progress bar
//
//                } else {
//                    //Something went wrong
//                }
//
//            }
//        });

    }


    private void loadContent() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    response = ApiCall.GET(client, RequestBuilder.buildURL());
                    //Parse the response string here
                    Log.d("Response", response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void attemptLogin(String url) {
        new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                try {
                    response = ApiCall.POST(
                            client,
                            params[0],
                            RequestBuilder.LoginBody("username", "password", "token"));
                    Log.d("Response", response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(url);
    }

}
