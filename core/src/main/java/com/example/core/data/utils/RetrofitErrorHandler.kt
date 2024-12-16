package com.example.core.data.utils

import android.util.Log
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Response

fun <T> retrofitErrorHandler(res: Response<T>): T{
    Log.i("retrofitBody", res.toString())
    if (res.isSuccessful) {
        val body = res.body()!!
        Log.i("retrofitBody", Gson().toJson(body))
        return body

    }else{
        val errorBody = res.errorBody()?.string()
        val errMsg = errorBody?.let {
            it
//            JSONObject(it).getString("errors")
        } ?: run {
            res.code().toString()
        }
        Log.i("retrofitBody", errMsg)
        throw Exception(errMsg)
    }
}