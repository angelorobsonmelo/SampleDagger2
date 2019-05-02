package br.com.angelorobson.sampledagger2.di.module.users

import br.com.angelorobson.sampledagger2.service.remote.user.UserApiDataSource
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class UserModule {

    @Provides
    fun provideUserApiDataSource(gson: Gson, okHttpClient: OkHttpClient): UserApiDataSource {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

        return retrofit.create<UserApiDataSource>(UserApiDataSource::class.java)
    }

}