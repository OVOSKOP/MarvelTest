package com.ovoskop.marveltest.domain.repository

import com.ovoskop.marveltest.data.dao.DataDAO
import com.ovoskop.marveltest.data.dao.ResultDAO

interface SearchRepository {
    suspend fun search(prefix: String, offset: Int, limit: Int) : DataDAO
}