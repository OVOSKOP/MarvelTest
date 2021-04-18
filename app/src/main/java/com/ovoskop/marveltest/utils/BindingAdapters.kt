package com.ovoskop.marveltest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:url")
fun getUrl(image: ImageView, url: String) {
    Picasso.get()
        .load(url)
        .into(image)
}