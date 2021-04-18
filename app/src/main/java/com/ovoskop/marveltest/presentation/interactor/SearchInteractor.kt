package com.ovoskop.marveltest.presentation.interactor

import com.ovoskop.marveltest.domain.entity.Character

interface SearchInteractor {
    suspend fun search(prefix: String, offset: Int, limit: Int) : List<Character>?
}