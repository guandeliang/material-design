package com.jacob.http

import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*
import java.util.concurrent.TimeUnit

object MdOkHttpClient {
    var client: OkHttpClient? = null
        get() {
            if (field == null) {
                try {
                    val specHttps = ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
                            .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0)
                            .allEnabledCipherSuites()
                            .build()
                    val specHttp = ConnectionSpec.Builder(ConnectionSpec.CLEARTEXT).build()
                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.NONE
                    field = OkHttpClient.Builder()
                            .readTimeout(60, TimeUnit.SECONDS)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .connectionSpecs(Arrays.asList(specHttps, specHttp))
                            .addInterceptor(logging)
                            .build()
                } catch (e: Exception) {
                }
            }
            return field
        }

}