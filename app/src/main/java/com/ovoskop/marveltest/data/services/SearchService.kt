package com.ovoskop.marveltest.data.services

import com.ovoskop.marveltest.data.dao.CharacterDAO
import com.ovoskop.marveltest.data.dao.DataDAO
import com.ovoskop.marveltest.data.dao.ResultDAO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("characters")
    fun search(
        @Query("nameStartsWith") prefix: String,
        @Query("apikey") key: String = "",
        @Query("hash") hash: String,
        @Query("ts") ts: Long,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("orderBy") order: String = "name"
    ) : Call<DataDAO>

}