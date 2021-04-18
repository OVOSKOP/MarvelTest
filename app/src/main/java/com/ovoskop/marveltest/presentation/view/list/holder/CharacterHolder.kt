package com.ovoskop.marveltest.presentation.view.list.holder

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ovoskop.marveltest.databinding.ItemListBinding
import com.ovoskop.marveltest.domain.entity.Character

class CharacterHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: Character?) {
        binding.root.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.character = character
    }

}