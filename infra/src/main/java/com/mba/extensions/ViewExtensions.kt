package com.mba.extensions

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.mba.utils.ViewConsts

fun View.setPaddingForSidesDP(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    setPadding(
          left?.let { ViewConsts.dp(it) } ?: paddingLeft,
        top?.let { ViewConsts.dp(it) } ?: paddingTop,
        right?.let { ViewConsts.dp(it) } ?: paddingRight,
        bottom?.let { ViewConsts.dp(it) } ?: paddingBottom
    )
}

fun View.setMarginsForSidesDP(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null
) {
    val lp = layoutParams as ViewGroup.MarginLayoutParams
    setMargins(
        left?.let { ViewConsts.dp(it) } ?: lp.leftMargin,
        top?.let { ViewConsts.dp(it) } ?: lp.topMargin,
        right?.let { ViewConsts.dp(it) } ?: lp.rightMargin,
        bottom?.let { ViewConsts.dp(it) } ?: lp.bottomMargin
    )
}

fun View.setVerticalMarginDP(margin: Int) {
    setMarginsForSidesDP(
        top = margin,
        bottom = margin
    )
}

fun View.setHorizontalMarginDP(margin: Int) {
    setMarginsForSidesDP(
        left = margin,
        right = margin
    )
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.isNotVisible(): Boolean = !isVisible()


fun View.show() {
    visibility = View.VISIBLE
}

fun show(vararg views: View) {
    views.forEach { it.show() }
}

fun View.showAnimated(duration: Long = 300) {
    alpha = 0f
    visibility = View.VISIBLE
    animate().setDuration(duration).alpha(1f)
}

fun View.gone() {
    visibility = View.GONE
}

fun gone(vararg views: View?) {
    views.forEach { it?.gone() }
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.goneAnimated(duration: Long = 300L) {
    animate().setDuration(duration).alpha(0f).withEndAction {
        visibility = View.GONE
    }
}

fun View.setMargins(margin: Int) {
    (layoutParams as ViewGroup.MarginLayoutParams).setMargins(margin, margin, margin, margin)
}

fun View.setMargins(left: Int? = null, top: Int? = null, right: Int? = null, bottom: Int? = null) =
    (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
        setMargins(
            left ?: leftMargin, top ?: topMargin, right ?: rightMargin, bottom
                ?: bottomMargin
        )
    }


fun View.showOrGone(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun View.showOrHide(show: Boolean) {
    visibility = if (show) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun View.setPaddingOnAllSidesDP(padding: Int) {
    val paddingDP = ViewConsts.dp(padding)

    setPadding(paddingDP, paddingDP, paddingDP, paddingDP)
}

fun View.setVerticalPaddingDP(padding: Int) {
    setPadding(paddingLeft, padding, paddingRight, padding)
}

fun View.setHorizontalPaddingDP(padding: Int) {
    setPadding(padding, paddingTop, padding, paddingBottom)
}