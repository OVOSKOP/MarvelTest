package com.ovoskop.marveltest.data.repository

import android.content.Context
import com.ovoskop.marveltest.R
import com.ovoskop.marveltest.data.dao.CharacterDAO
import com.ovoskop.marveltest.data.dao.DataDAO
import com.ovoskop.marveltest.data.dao.ResultDAO
import com.ovoskop.marveltest.data.factory.ServiceFactory
import com.ovoskop.marveltest.data.services.SearchService
import com.ovoskop.marveltest.domain.repository.SearchRepository
import com.ovoskop.marveltest.utils.md5
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SearchRepositoryImpl(private val context: Context, private val serviceFactory: ServiceFactory) : SearchRepository {
    override suspend fun search(prefix: String, offset: Int, limit: Int): DataDAO {
        return suspendCoroutine { continuation ->
            val searchService = serviceFactory.create(SearchService::class.java)
            val apiKey = context.getString(R.string.public_key_api)
            val privateApiKey = context.getString(R.string.private_key_api)
            val ts = Date().time
            val hash = (ts.toString() + privateApiKey + apiKey).md5()

            println("$ts $hash")

            val call = searchService.search(prefix, apiKey, hash, ts, limit, offset)
            call.enqueue( object : Callback<DataDAO> {
                override fun onResponse(
                    call: Call<DataDAO>,
                    response: Response<DataDAO>
                ) {
                    if (response.isSuccessful) {
                        val characters = response.body()
                        characters?.apply {
                            continuation.resume(this)
                        }

                    } else {
                        println("error ${response.errorBody()?.string()}")
                        continuation.resumeWithException(Exception())
                    }
                }

                override fun onFailure(call: Call<DataDAO>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }
}