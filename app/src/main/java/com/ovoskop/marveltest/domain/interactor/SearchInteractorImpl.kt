package com.ovoskop.marveltest.domain.interactor

import com.ovoskop.marveltest.domain.entity.Character
import com.ovoskop.marveltest.domain.repository.SearchRepository
import com.ovoskop.marveltest.presentation.interactor.SearchInteractor

class SearchInteractorImpl(private val searchRepository: SearchRepository) : SearchInteractor {
    override suspend fun search(prefix: String, offset: Int, limit: Int): List<Character>? {
        val result = searchRepository.search(prefix, offset, limit)
        return result
            .data
            ?.results
            ?.map { it.toCharacter() }
    }
}