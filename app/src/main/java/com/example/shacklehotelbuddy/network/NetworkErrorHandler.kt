package com.example.shacklehotelbuddy.network

import android.content.Context
import androidx.annotation.StringRes
import com.example.shacklehotelbuddy.R
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection.HTTP_GATEWAY_TIMEOUT
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkErrorHandler @Inject constructor(val resourceProvider: ResourceProvider) {

    suspend inline fun <T> handle(
        crossinline apiCall: suspend () -> T
    ): Result<T> {
        return try {
            Result.Success(
                withContext(CoroutineScope(Dispatchers.IO).coroutineContext) { apiCall.invoke() }
            )
        } catch (e: Throwable) {
            // TODO create Factory
            when (e) {
                is IOException -> Result.Error(resourceProvider.getString(R.string.error_connection))
                is HttpException -> {
                    createError(e)
                }

                else -> Result.Error(e.message.toString())
            }

        }
    }

    fun createError(e: HttpException): Result.Error {
        return when (e.code()) {
            HTTP_UNAUTHORIZED -> Result.Error(resourceProvider.getString(R.string.error_unauthorized))
            HTTP_GATEWAY_TIMEOUT -> Result.Error(resourceProvider.getString(R.string.error_connection))
            else -> Result.Error(resourceProvider.getString(R.string.error_service))
        }
    }
}

interface ResourceProvider {

    fun getString(@StringRes resId: Int): String
}

class ResourceProviderImpl(@ApplicationContext val context: Context) : ResourceProvider {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}