package com.alexeybondarenko.mvvmtest2.utils.retrofit

import com.alexeybondarenko.mvvmtest2.model.UserModel
import kotlin.math.log

class UsersResponse (
    private val login: String? = null,
    private val id: Int? = null,
    private val node_id:  String? = null,
    private val avatar_url:  String? = null,
    private val gravatar_id:  String? = null,
    private val url:  String? = null,
    private val html_url:  String? = null,
    private val followers_url:  String? = null,
    private val following_url:  String? = null,
    private val gists_url:  String? = null,
    private val starred_url:  String? = null,
    private val subscriptions_url:  String? = null,
    private val organizations_url:  String? = null,
    private val repos_url:  String? = null,
    private val events_url:  String? = null,
    private val received_events_url:  String? = null,
    private val type:  String? = null,
    private val site_admin: Boolean? = null,
) {
    fun mapToModel(): UserModel {
        return UserModel(
            id= id,
            login = login,
            avatarUrl = avatar_url
        )
    }
}