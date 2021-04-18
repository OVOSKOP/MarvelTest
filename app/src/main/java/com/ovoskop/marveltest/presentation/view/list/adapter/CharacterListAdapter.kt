package com.ovoskop.marveltest.presentation.view.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.marveltest.databinding.ItemListBinding
import com.ovoskop.marveltest.domain.entity.Character
import com.ovoskop.marveltest.presentation.view.list.holder.CharacterHolder

class CharacterListAdapter(diffUtil: DiffUtil.ItemCallback<Character>) : PagedListAdapter<Character, CharacterHolder>(diffUtil) {

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayoutManager.VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CharacterHolder(ItemListBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(getItem(position))
    }

}