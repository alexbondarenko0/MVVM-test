package com.alexeybondarenko.mvvmtest2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexeybondarenko.mvvmtest2.model.UserModel
import com.alexeybondarenko.mvvmtest2.model.UsersListModel
import com.alexeybondarenko.mvvmtest2.utils.retrofit.NetworkService
import com.alexeybondarenko.mvvmtest2.utils.retrofit.UsersResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UsersViewModel : ViewModel() {

    private var model: UsersListModel = UsersListModel(mutableListOf())

    val usersListLiveData = MutableLiveData<UsersListModel>()
    val showUpdatingProcess = MutableLiveData<Boolean>()

    // Кнопка Обновить
    fun updateUsersList() {
        getUsersListModel()
    }

    // Кнопка Удалить случайного
    fun deleteRandomUser() {
        if (model.list.isNotEmpty()) {
            val randomIndex: Int = Random().nextInt(model.list.size)
            model.list.removeAt(randomIndex)
            usersListLiveData.postValue(model)
        } else {
            Log.d("UsersViewModel", "Users list is empty")
        }
    }

    private fun getUsersListModel() {
        viewModelScope.launch {
            try {
                showUpdatingProcess.postValue(true)
                val result: MutableList<UserModel> = mutableListOf()

                NetworkService.instance
                    ?.newsApi
                    ?.getUsersList()
                    ?.enqueue(object : Callback<List<UsersResponse>> {
                        override fun onResponse(
                            call: Call<List<UsersResponse>>,
                            response: Response<List<UsersResponse>>
                        ) {
                            response.body()?.forEach {
                                result.add(it.mapToModel())
                            }
                            model = UsersListModel(result)
                            usersListLiveData.postValue(model)
                        }

                        override fun onFailure(call: Call<List<UsersResponse>>, t: Throwable) {
                            Log.e("UsersViewModel", "onFailure")
                            model = UsersListModel(result)
                            usersListLiveData.postValue(model)
                        }
                    })

            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                showUpdatingProcess.postValue(false)
            }
        }
    }

}