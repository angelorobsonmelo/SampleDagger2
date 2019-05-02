package br.com.angelorobson.sampledagger2.model

import javax.inject.Inject

data class User @Inject constructor(
    val id: Int,
    val name: String,
    val username: String,
    val email: String)