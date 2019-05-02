package br.com.angelorobson.sampledagger2.modules.users.users

import br.com.angelorobson.sampledagger2.model.User
import br.com.angelorobson.sampledagger2.service.remote.user.UserApiDataSource
import br.com.angelorobson.sampledagger2.utils.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UsersViewModel @Inject constructor(
    private val userApiDataSource: UserApiDataSource
) : BaseViewModel<MutableList<User>>() {

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