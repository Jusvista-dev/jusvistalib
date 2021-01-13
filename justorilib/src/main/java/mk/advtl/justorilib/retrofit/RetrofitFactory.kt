package mk.advtl.justorilib.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    //Base URL Live
    val BASE_URL = "https://www.justori.com/justori/webservice/"

    fun getApiService(): RestInterface {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.readTimeout(300, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())

        return retrofitBuilder.build().create<RestInterface>(RestInterface::class.java!!)
    }

}