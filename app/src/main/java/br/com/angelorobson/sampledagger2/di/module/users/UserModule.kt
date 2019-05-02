package br.com.angelorobson.sampledagger2.di.module.users

import br.com.angelorobson.sampledagger2.service.remote.ApiDataSource.Companion.createService
import br.com.angelorobson.sampledagger2.service.remote.user.UserApiDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {

    @Provides
    @Singleton
    fun provideUsersApiDataSource(): UserApiDataSource {
       return createService(UserApiDataSource::class.java)
    }

}