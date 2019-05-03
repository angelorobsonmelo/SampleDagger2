package br.com.angelorobson.sampledagger2.di.module.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetWorkModule {

    // TODO cache later
   /* @Provides
    fun provideFile(context: Context): File {
        val file = File(context.filesDir, "cache_dir")
        if (!file.exists())
            file.mkdirs()
        return file
    }

    @Provides
    fun provideCache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1000 * 1000)  // 10 MiB cache
    }*/

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)

        return httpClient.addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }


}