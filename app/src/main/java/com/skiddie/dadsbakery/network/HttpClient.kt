package com.skiddie.dadsbakery.network

import android.util.Log
import com.skiddie.dadsbakery.BuildConfig
import com.skiddie.dadsbakery.utils.Helpers
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpClient {

    private var client: Retrofit? = null
    private var endpoint: com.skiddie.dadsbakery.network.EndPoint? = null

    companion object {
        private val mInstance: com.skiddie.dadsbakery.network.HttpClient =
            com.skiddie.dadsbakery.network.HttpClient()
        @Synchronized
        fun getInstance(): com.skiddie.dadsbakery.network.HttpClient {
            return com.skiddie.dadsbakery.network.HttpClient.Companion.mInstance
        }
    }

    init {
        buildRetrofitClient()
    }

    fun getApi(): com.skiddie.dadsbakery.network.EndPoint? {
        if (endpoint == null) {
            endpoint = client!!.create(com.skiddie.dadsbakery.network.EndPoint::class.java)
        }
        return endpoint
    }

    private fun buildRetrofitClient() {
        val token = com.skiddie.dadsbakery.FoodMarket.getApp().getToken()
        buildRetrofitClient(token)
    }

    fun buildRetrofitClient(token: String?) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(2, TimeUnit.MINUTES)
        builder.readTimeout(2, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor(ChuckInterceptor(com.skiddie.dadsbakery.FoodMarket.getApp()))
        }

        if (token != null) {
            builder.addInterceptor(getInterceptorWithHeader("Authorization", "Bearer $token"))
//            builder.addInterceptor(getInterceptorWithHeader("Authorization", "${token}"))
//            Log.d("TOKEN", "token: $token")
        }

        val okHttpClient = builder.build()
        client = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL+"api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Helpers.getDefaultGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        endpoint = null
        Log.v("fan", token.toString())
    }


    private fun getInterceptorWithHeader(headerName: String, headerValue: String): Interceptor {
        val header = HashMap<String, String>()
        header[headerName] = headerValue
        return getInterceptorWithHeader(header)
    }


    private fun getInterceptorWithHeader(headers: Map<String, String>): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            for ((key, value) in headers) {
                builder.addHeader(key, value)
            }
            builder.method(original.method, original.body)
            chain.proceed(builder.build())
        }
    }
}