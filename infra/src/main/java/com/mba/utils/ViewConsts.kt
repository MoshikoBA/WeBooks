package com.mba.utils

object ViewConsts {

    val scale = 0f

    fun dp(dps: Int): Int {
        return (dps * scale + 0.5f).toInt()
    }
}