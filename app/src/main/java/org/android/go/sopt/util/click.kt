package org.android.go.sopt.util

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onThrottleClick", "clickInterval", requireAll = false)
fun applyThrottleClick(view: View, listener: View.OnClickListener, interval: Long? = 300L) {
    val throttleListener = interval?.let { time ->
        OnThrottleClickListener(listener, time)
    } ?: OnThrottleClickListener(listener)
    view.setOnClickListener(throttleListener)
}