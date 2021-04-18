package com.ovoskop.marveltest.utils

import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Room
import com.ovoskop.marveltest.data.factory.OkHttpFactory
import com.ovoskop.marveltest.data.factory.RetrofitFactory
import com.ovoskop.marveltest.data.factory.ServiceFactory
import com.ovoskop.marveltest.data.repository.CharacterRepositoryImpl
import com.ovoskop.marveltest.data.repository.SearchRepositoryImpl
import com.ovoskop.marveltest.data.room.AppDatabase
import com.ovoskop.marveltest.domain.entity.Character
import com.ovoskop.marveltest.domain.interactor.CharacterInteractorImpl
import com.ovoskop.marveltest.domain.interactor.SearchInteractorImpl
import com.ovoskop.marveltest.domain.repository.CharacterRepository
import com.ovoskop.marveltest.domain.repository.SearchRepository
import com.ovoskop.marveltest.presentation.interactor.CharacterInteractor
import com.ovoskop.marveltest.presentation.interactor.SearchInteractor
import com.ovoskop.marveltest.presentation.view.list.adapter.CharacterListAdapter
import com.ovoskop.marveltest.presentation.view.list.datasource.CharacterDataSource
import com.ovoskop.marveltest.presentation.view.list.diffutil.CharacterDiffUtilCallback
import com.ovoskop.marveltest.presentation.view.viewmodel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.Executors

val mainModule = module {
    viewModel { MainViewModel() }
}

val networkModule = module {
    single { OkHttpFactory() }
    single { RetrofitFactory(get()) }

    single { ServiceFactory(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get(), get()) }

    single<SearchInteractor> { SearchInteractorImpl(get()) }
}

val listModule = module {
    factory { params -> CharacterDataSource(get(), get(), get(), searchTxt = params.get()) }
    factory<DiffUtil.ItemCallback<Character>> { CharacterDiffUtilCallback() }
    factory { CharacterListAdapter(get()) }

    single<PagedList.Config> {
        PagedList.Config.Builder().apply {
            setEnablePlaceholders(false)
            setInitialLoadSizeHint(10)
            setPageSize(10)
        }.build()
    }

    factory { params ->
        val pagedList: PagedList<Character> = PagedList.Builder(params.get<CharacterDataSource>(), get<PagedList.Config>())
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .setNotifyExecutor(MainThreadExecutor())
            .build()

        pagedList
    }
}

val dbModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "_cache_db_")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().characterDAO() }
    single<CharacterRepository> { CharacterRepositoryImpl(get()) }
    single<CharacterInteractor> { CharacterInteractorImpl(get()) }
}