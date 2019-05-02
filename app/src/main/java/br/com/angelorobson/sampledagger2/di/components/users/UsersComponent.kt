package br.com.angelorobson.sampledagger2.di.components.users

import br.com.angelorobson.sampledagger2.di.module.users.UserModule
import br.com.angelorobson.sampledagger2.di.module.users.UserViewModelModule
import br.com.angelorobson.sampledagger2.modules.users.users.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class, UserViewModelModule::class])
interface UsersComponent {

    fun inject(usersFragment: UsersFragment)

}