package com.example.core.data.data_source.manager

import android.content.Context
import android.content.SharedPreferences
import com.example.core.data.model.UserModelImpl
import com.example.core.domain.manager.UserManager
import com.example.core.domain.models.UserModel
import com.google.gson.Gson

class UserMangerImpl(
    private val context: Context
): UserManager {

    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE)

    override suspend fun getUser(): UserModel? {
        val json = sharedPreferences.getString("user", null)
        if (json == null){
            return json
        }

        return Gson().fromJson(json, UserModelImpl::class.java)
    }

    override suspend fun saveUser(model: UserModel?) {
        sharedPreferences.edit()
            .clear()
            .putString("user", Gson().toJson(model))
            .apply()
    }
}