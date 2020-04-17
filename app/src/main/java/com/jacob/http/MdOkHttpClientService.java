package com.jacob.http;

import android.util.Log;

import com.jacob.temp.TempConstant;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;

public class MdOkHttpClientService {
    private static OkHttpClient okHttpClient = null;

    public static OkHttpClient getOkHttpClient(){
        if(okHttpClient == null){
            try {
                ConnectionSpec specHttps = new ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                        .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
                        .allEnabledCipherSuites()
                        .build();//解决在Android5.0版本以下https无法访问
                ConnectionSpec specHttp = new ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT).build();//兼容http接口

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .connectionSpecs(Arrays.asList(specHttps,specHttp))
                        .addInterceptor(logging)
                        .build();
            }catch (Exception e){

            }

        }
        return okHttpClient;
    }


}
