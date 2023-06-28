package com.example.popularlibraries.common

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.popularlibraries.R

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun ImageView.loadGlide(url: String?) {
    Glide.with(context).load(url).transform(CircleCrop()).placeholder(R.drawable.ic_user_placeholder)
        .into(this)
}