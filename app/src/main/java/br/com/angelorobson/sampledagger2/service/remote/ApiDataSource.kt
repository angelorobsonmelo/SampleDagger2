//package br.com.angelorobson.sampledagger2.service.remote
//
//import com.google.gson.GsonBuilder
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object ApiDataSource {
//
//    @JvmStatic
//    fun <S> createService(serviceClass: Class<S>): S {
//        val loggingInterceptor = HttpLoggingInterceptor()
//        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        val httpClient = OkHttpClient.Builder()
//            .readTimeout(15, TimeUnit.SECONDS)
//
//        httpClient.addInterceptor(loggingInterceptor)
//
//        val gson = GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://jsonplaceholder.typicode.com/")
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(httpClient.build())
//            .build()
//
//        return retrofit.create(serviceClass)
//    }
//}
