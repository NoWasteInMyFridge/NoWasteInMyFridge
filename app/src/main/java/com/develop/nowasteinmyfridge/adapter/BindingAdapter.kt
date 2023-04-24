package com.develop.nowasteinmyfridge.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.develop.nowasteinmyfridge.R

@BindingAdapter("iconUrl")
fun ImageView.loadIconUrl(url: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadIconUrl.context)) }
        .build()


    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .placeholder(R.drawable.loading_placeholder)
        .error(R.drawable.ic_broken_image)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)
}