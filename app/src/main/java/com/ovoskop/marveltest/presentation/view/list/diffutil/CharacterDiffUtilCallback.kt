package com.ovoskop.marveltest.presentation.view.list.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.ovoskop.marveltest.domain.entity.Character

class CharacterDiffUtilCallback : DiffUtil.ItemCallback<Character>() {

    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem.hashCode() == newItem.hashCode()

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
        oldItem == newItem

}