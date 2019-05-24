/**
 * @author Md Faruk Hossain
 * FIXME
 */

package com.faruk.retrofitshimmer.webservice;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpClientInstance {

    private static OkHttpClient okHttpClient;

    public static OkHttpClient getOKHttpClientInstance() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                    Request originalRequest = chain.request();

                    Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                            Credentials.basic("aUsername", "aPassword"));

                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                }
            }).build();
        }
        return okHttpClient;
    }

}
