package br.com.angelorobson.sampledagger2.di.components.users

import br.com.angelorobson.sampledagger2.di.module.network.NetWorkModule
import br.com.angelorobson.sampledagger2.di.module.users.UserModule
import br.com.angelorobson.sampledagger2.di.module.users.UserViewModelModule
import br.com.angelorobson.sampledagger2.modules.users.users.UsersFragment
import br.com.angelorobson.sampledagger2.service.remote.user.UserApiDataSource
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class, UserViewModelModule::class, NetWorkModule::class])
interface UsersComponent {

    fun inject(usersFragment: UsersFragment)
}