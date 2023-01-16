package com.alexeybondarenko.mvvmtest2.utils

import android.content.Context
import com.alexeybondarenko.mvvmtest2.model.UsersListModel
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStreamReader


class DBHelper {
    private val FILE_NAME = "users_data.json"

    fun saveToDB(context: Context, dataList: UsersListModel): Boolean {
        val gson = Gson()
        val dataItems = DataItems()
        dataItems.usersModel = dataList
        val jsonString = gson.toJson(dataItems)
        try {
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { fileOutputStream ->
                fileOutputStream.write(jsonString.toByteArray())
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun importFromDB(context: Context): UsersListModel {
        try {
            context.openFileInput(FILE_NAME).use { fileInputStream ->
                InputStreamReader(fileInputStream).use { streamReader ->
                    val gson = Gson()
                    val dataItems: DataItems = gson.fromJson(streamReader, DataItems::class.java)
                    return dataItems.usersModel
                }
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return UsersListModel(mutableListOf())
    }

    private class DataItems {
        var usersModel: UsersListModel = UsersListModel(mutableListOf())
    }
}