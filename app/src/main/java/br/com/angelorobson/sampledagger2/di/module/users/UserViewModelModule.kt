package br.com.angelorobson.sampledagger2.di.module.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.angelorobson.sampledagger2.di.util.ViewModelFactory
import br.com.angelorobson.sampledagger2.di.util.ViewModelKey
import br.com.angelorobson.sampledagger2.modules.users.users.UsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UserViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    internal abstract fun usersViewModel(viewModel: UsersViewModel): ViewModel

}