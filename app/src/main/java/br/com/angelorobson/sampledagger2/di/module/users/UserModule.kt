package br.com.angelorobson.sampledagger2.di.module.users

import br.com.angelorobson.sampledagger2.di.module.network.NetWorkModule
import br.com.angelorobson.sampledagger2.service.remote.user.UserApiDataSource
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module(includes = [NetWorkModule::class, UserViewModelModule::class])
class UserModule {

    @Provides
    fun provideUserApiDataSource(retrofit: Retrofit): UserApiDataSource {
        return retrofit.create<UserApiDataSource>(UserApiDataSource::class.java)
    }

}