package br.com.angelorobson.sampledagger2.modules.users.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.angelorobson.sampledagger2.model.User
import br.com.angelorobson.sampledagger2.service.remote.user.UserApiDataSource
import br.com.angelorobson.sampledagger2.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UsersViewModel(
    private val userApiDataSource: UserApiDataSource
) : BaseViewModel<MutableList<User>>(), ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel(userApiDataSource) as T
    }

    init {
        getAll()
    }

    fun getAll() {
        val subscribe = userApiDataSource.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoadingObserver.value = true }
            .doAfterTerminate { isLoadingObserver.value = false }
            .subscribe(
                {
                    successObserver.value = it
                },
                { throwable ->
                    errorObserver.value = throwable.localizedMessage
                }
            )

        subscriptions.add(subscribe)
    }
}