package br.com.angelorobson.sampledagger2.service.remote.user

import br.com.angelorobson.sampledagger2.model.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UserApiDataSource {

    @GET("users")
    fun getAll(): Observable< MutableList<User>>
}