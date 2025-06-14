package com.example.core.di

import com.example.core.data.data_source.network.AuthApi
import com.example.core.data.data_source.network.AuthorsApi
import com.example.core.data.data_source.network.DepartmentApi
import com.example.core.data.data_source.network.ExecutorApi
import com.example.core.data.data_source.network.PasswordResetApi
import com.example.core.data.data_source.network.RequestTypeApi
import com.example.core.data.data_source.network.TicketApi
import com.example.core.data.data_source.network.UserApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

val apiModule = module {

    val host = "https://81.177.174.94"
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .setLenient()
        .create()

    single<AuthApi> {
        Retrofit.Builder()
            .baseUrl("${host}:7086")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuthApi::class.java)
    }

    single<PasswordResetApi> {

        Retrofit.Builder()
            .baseUrl("${host}:7086")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PasswordResetApi::class.java)
    }


    single<TicketApi> {
        Retrofit.Builder()
                .baseUrl("${host}:7215")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TicketApi::class.java)
    }

    single<RequestTypeApi> {
        Retrofit.Builder()
            .baseUrl("${host}:7215")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RequestTypeApi::class.java)
    }

    single<DepartmentApi> {
        Retrofit.Builder()
            .baseUrl("${host}:7215")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(DepartmentApi::class.java)
    }



    single<UserApi> {
        Retrofit.Builder()
            .baseUrl("${host}:5235")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(UserApi::class.java)
    }

    single<ExecutorApi> {
        Retrofit.Builder()
            .baseUrl("${host}:5235")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ExecutorApi::class.java)
    }

    single<AuthorsApi> {
        Retrofit.Builder()
            .baseUrl("${host}:5235")
            .client(getUnsafeOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(AuthorsApi::class.java)
    }
}

private fun getUnsafeOkHttpClient(): OkHttpClient {
    return try {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

            override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {}

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> = arrayOf()
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, java.security.SecureRandom())
        val sslSocketFactory = sslContext.socketFactory

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // Уровень логгирования (BODY показывает всё)
        }

        OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            .hostnameVerifier { _, _ -> true }
            .addInterceptor(loggingInterceptor)
            .build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}