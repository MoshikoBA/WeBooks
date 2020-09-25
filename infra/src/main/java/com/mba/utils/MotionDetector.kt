package com.mba.utils

import android.view.MotionEvent

class MotionDetector(private val tolerance: Int) {
    private var xDistance = 0f
    private var yDistance = 0f
    private var lastX = 0f
    private var lastY = 0f
    private var state = State.None

    enum class State {
        HorizontalLeft, HorizontalRight, VerticalUp, VerticalDown, None, Multitouch
    }

    fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.pointerCount > 1) {
            setState(State.Multitouch)
        } else {
            when (ev.action) {
                MotionEvent.ACTION_DOWN -> {
                    run {
                        yDistance = 0f
                        xDistance = yDistance
                    }
                    lastX = ev.x
                    lastY = ev.y
                    setState(State.None)
                }
                MotionEvent.ACTION_MOVE -> if (state != State.None) {
                    // do nothing, we are locked
                } else {
                    val curX = ev.x
                    val curY = ev.y
                    xDistance += Math.abs(curX - lastX)
                    yDistance += Math.abs(curY - lastY)
                    if (xDistance > yDistance && xDistance > tolerance) {
                        if (curX > lastX) {
                            setState(State.HorizontalRight)
                        } else {
                            setState(State.HorizontalLeft)
                        }
                    } else if (yDistance > xDistance && yDistance > tolerance) {
                        if (curY > lastY) {
                            setState(State.VerticalDown)
                        } else {
                            setState(State.VerticalUp)
                        }
                    }
                    lastX = curX
                    lastY = curY
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> setState(State.None)
            }
        }
        return state != State.None
    }

    fun log(message: String?) {
        // App.logger().d(MotionDetector.class.getSimpleName(), message);
    }

    fun getState(): State {
        return state
    }

    fun setState(state: State) {
        log(state.name)
        this.state = state
    }

    val isHorizontalScroll: Boolean
        get() = state == State.HorizontalLeft || state == State.HorizontalRight

    val isVerticalScroll: Boolean
        get() = state == State.VerticalDown || state == State.VerticalUp

}