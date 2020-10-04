package com.mba.utils

import android.content.res.Resources

object ViewConsts {

    val scale = Resources.getSystem().displayMetrics.density

    fun dp(dps: Int): Int {
        return (dps * scale + 0.5f).toInt()
    }
}