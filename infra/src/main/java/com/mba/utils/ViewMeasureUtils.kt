package com.mba.utils

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object ViewMeasureUtils {

    fun isPointInsideView(x: Float, y: Float, view: View): Boolean {
        val location = IntArray(2)
        view.getLocationOnScreen(location)
        val viewX = location[0]
        val viewY = location[1]
        return x > viewX && x < viewX + view.width && y > viewY && y < viewY + view.height
    }

    fun isViewReasonablyVisible(view: View?, parentView: RecyclerView, ratio: Float): Boolean {
        return if (parentView.layoutManager is LinearLayoutManager && RecyclerView.HORIZONTAL == (parentView.layoutManager as LinearLayoutManager?)!!.orientation) {
            ViewMeasureUtils.isViewHorizontallyReasonablyVisible(view, parentView, ratio)
        } else {
            ViewMeasureUtils.isViewVerticalReasonablyVisible(view, parentView, ratio)
        }
    }

    private fun isViewVerticalReasonablyVisible(
        view: View?,
        parentView: ViewGroup?,
        ratio: Float
    ): Boolean {
        if (view == null || parentView == null || view.measuredHeight == 0) {
            return false
        }

        val viewTop =
            if (view.parent === parentView) {
                view.top
            } else {
                ViewMeasureUtils.getTop(view, parentView)
            }

        val parentViewTop = parentView.top
        val parentViewBottom = parentView.bottom
        val viewHeight = view.measuredHeight
        val viewBottom = viewTop + viewHeight

        // view is bigger than its parent
        if (viewTop < parentViewTop && viewBottom > parentViewBottom) {
            return true
        }

        // view is inside its parents' bounds
        if (viewTop <= parentViewBottom &&
            viewBottom >= parentViewTop
        ) {

            // view is visible from top
            if (viewBottom < parentViewBottom) {
                return viewBottom - parentViewTop >= ratio * viewHeight
            } else if (viewTop >= parentViewTop) {
                return parentViewBottom - viewTop >= ratio * viewHeight
            }
        }
        return false
    }

    private fun isViewHorizontallyReasonablyVisible(
        view: View?,
        parentView: ViewGroup?,
        ratio: Float
    ): Boolean {
        if (view == null || parentView == null || view.measuredWidth == 0) {
            return false
        }

        val viewLeft =
            if (view.parent === parentView) {
                view.left
            } else {
                ViewMeasureUtils.getLeft(view, parentView)
            }

        val parentViewLeft = parentView.left
        val parentViewRight = parentView.right
        val viewWidth = view.measuredWidth
        val viewRight = viewLeft + viewWidth

        // view is bigger than its parent
        if (viewLeft < parentViewLeft && viewRight > parentViewRight) {
            return true
        }

        // view is inside its parents' bounds
        if (viewLeft <= parentViewRight &&
            viewRight >= parentViewLeft
        ) {

            // view is visible from top
            if (viewRight < parentViewRight) {
                return viewRight - parentViewLeft >= ratio * viewWidth
            } else if (viewLeft >= parentViewLeft) {
                return parentViewRight - viewLeft >= ratio * viewWidth
            }
        }
        return false
    }

    private fun getTop(view: View, parentView: ViewGroup): Int {
        var view = view
        var top = view.top
        while (view.parent !== parentView) {
            view = view.parent as View
            top += view.top
        }
        return top
    }

    private fun getLeft(view: View, parentView: ViewGroup): Int {
        var view = view
        var left = view.left
        while (view.parent !== parentView) {
            view = view.parent as View
            left += view.left
        }
        return left
    }
}