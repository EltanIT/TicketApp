package com.example.core.data.utils

import android.os.Build
import com.example.core.data.model.UserModelImpl
import com.example.core.domain.models.UserModel
import org.json.JSONObject
import java.util.Base64

public fun decodeToken(jwt: String): UserModel {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) throw Exception("Error parsing JWT: $jwt")
    val parts = jwt.split(".")
    return try {
        val charset = charset("UTF-8")
        val payload = String(Base64.getUrlDecoder().decode(parts[1].toByteArray(charset)), charset)

        val json = JSONObject(payload)
        val role = json.getString("role")
        val userId = json.getString("userId").toInt()
        val email = json.getString("email")
        val fio = json.getString("fio")
        val numberPhone = json.getString("numberPhone")

        UserModelImpl(
            userId,
            "",
            email,
            role,
            fio,
            numberPhone
        )
    } catch (e: Exception) {
        throw Exception("Error parsing JWT: $e")
    }
}