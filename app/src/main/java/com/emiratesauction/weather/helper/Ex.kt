package com.emiratesauction.weather.helper

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.emiratesauction.weather.BuildConfig
import com.emiratesauction.weather.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by µðšţãƒâ ™ on 03/06/2021.
 *  ->
 */

fun ImageView.loadUrl(url: String?) {
    url?.let {
        if (it.isNotEmpty())
            Glide.with(context)
                .load(BuildConfig.URL_STORAGE + "$it@2x.png")
                .into(this)
    }
}

fun TextView.temp(double: Double?) {
    double?.let { text = context.getString(R.string.temp, it / 10) }
}

fun TextView.date(dateLong: Long?) {
    dateLong?.let {
        text = SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH).format(it * 1000)
    }
}

fun View.hideSoftInput() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

