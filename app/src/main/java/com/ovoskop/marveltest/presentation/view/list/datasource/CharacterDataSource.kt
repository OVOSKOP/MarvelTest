package com.ovoskop.marveltest.presentation.view.list.datasource

import android.content.Context
import androidx.paging.PositionalDataSource
import com.ovoskop.marveltest.domain.entity.Character
import com.ovoskop.marveltest.presentation.interactor.CharacterInteractor
import com.ovoskop.marveltest.presentation.interactor.SearchInteractor
import com.ovoskop.marveltest.presentation.view.`interface`.OnExceptionList
import com.ovoskop.marveltest.utils.isNetworkConnected
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CharacterDataSource(private val context: Context, private val searchInteractor: SearchInteractor, private val characterInteractor: CharacterInteractor, val searchTxt: String) :
    PositionalDataSource<Character>() {

    private var onError: OnExceptionList? = null

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Character>) {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                onError?.onLoad()
            }
            println(searchTxt)
            try {
                val res: List<Character> = if (isNetworkConnected(context)) {
                        searchInteractor.search(
                            searchTxt,
                            params.requestedStartPosition,
                            params.requestedLoadSize
                        )
                            ?: listOf()
                } else {
                    characterInteractor.getByPrefix(
                        "%$searchTxt%",
                        params.requestedStartPosition,
                        params.requestedLoadSize
                    )
                }

                println(res)
                println(res.size)
                try {
                    characterInteractor.insertCache(res)
                } catch (e: Exception) { }
                withContext(Dispatchers.Main) {
                    onError?.onSuccess(res.size)
                }
                callback.onResult(res, 0)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError?.onError(e)
                }
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Character>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val res: List<Character> = if (isNetworkConnected(context)) {
                    searchInteractor.search(
                        searchTxt,
                        params.startPosition,
                        params.loadSize
                    )
                        ?: listOf()
                } else {
                    characterInteractor.getByPrefix(
                        "%$searchTxt%",
                        params.startPosition,
                        params.loadSize
                    )
                }

                println(res)
                println(res.size)
                try {
                    characterInteractor.insertCache(res)
                } catch (e: Exception) { }
                callback.onResult(res)
            } catch (e: Exception) {

            }
        }
    }

    fun setOnCallbackListener(onExceptionList: OnExceptionList) {
        onError = onExceptionList
    }

}