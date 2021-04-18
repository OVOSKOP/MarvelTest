package com.ovoskop.marveltest.presentation.view.activity

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.ovoskop.marveltest.databinding.ActivityMainBinding
import com.ovoskop.marveltest.domain.entity.Character
import com.ovoskop.marveltest.presentation.view.list.adapter.CharacterListAdapter
import com.ovoskop.marveltest.presentation.view.list.datasource.CharacterDataSource
import com.ovoskop.marveltest.presentation.view.viewmodel.MainViewModel
import com.ovoskop.marveltest.utils.hideKeyword
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val adapter: CharacterListAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        binding.viewModel = mainViewModel

        binding.list.layoutManager = LinearLayoutManager(this)
        binding.list.adapter = adapter

        binding.search.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val searchText = textView.text

                if (searchText.length >= 3) {
                    val dataSource: CharacterDataSource by inject { parametersOf(searchText.toString()) }
                    dataSource.setOnCallbackListener(mainViewModel.onError)

                    val pagedList: PagedList<Character> by inject { parametersOf(dataSource) }

                    adapter.submitList(pagedList)
                    hideKeyword()
                    return@setOnEditorActionListener false
                } else {
                    return@setOnEditorActionListener true
                }
            }
            false
        }
    }
}