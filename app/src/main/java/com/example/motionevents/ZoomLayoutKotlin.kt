package com.example.motionevents

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.OnScaleGestureListener
import android.view.View
import android.widget.FrameLayout

class ZoomLayoutKotlin : FrameLayout, OnScaleGestureListener {
    private enum class Mode {
        NONE, DRAG, ZOOM
    }

    private var mode = Mode.NONE
    private var scale = 1.0f
    private var lastScaleFactor = 0f

    // Where the finger first  touches the screen
    private var startX = 0f
    private var startY = 0f

    // How much to translate the canvas
    private var dx = 0f
    private var dy = 0f
    private var prevDx = 0f
    private var prevDy = 0f

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(context)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        init(context)
    }

    var canZoom = true
    private var scaleDetector: ScaleGestureDetector? = null

    @SuppressLint("ClickableViewAccessibility")
    private fun init(context: Context) {
        scaleDetector = ScaleGestureDetector(context, this)
    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        if (!canZoom) {
            Log.i(
                TAG,
                "zoom model- touch event: canZoom is FALSE"
            )
            return false
        }
        Log.i(
            TAG,
            "zoom model- touch event action " + motionEvent.action + " action mask: " + MotionEvent.ACTION_MASK
        )
        when (motionEvent.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                Log.i(TAG, "zoom model- touch event: DOWN")
                if (scale > MIN_ZOOM) {
                    mode = Mode.DRAG
                    startX = motionEvent.x - prevDx
                    startY = motionEvent.y - prevDy
                }
            }
            MotionEvent.ACTION_MOVE -> if (mode == Mode.DRAG) {
                dx = motionEvent.x - startX
                dy = motionEvent.y - startY
                Log.i(TAG, "zoom model- touch event: MOVE")
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                mode = Mode.ZOOM
                Log.i(
                    TAG,
                    "zoom model- touch event: ACTION_POINTER_DOWN"
                )
            }
            MotionEvent.ACTION_POINTER_UP -> {
                mode = Mode.DRAG
                Log.i(
                    TAG,
                    "zoom model- touch event: ACTION_POINTER_UP"
                )
            }
            MotionEvent.ACTION_UP -> {
                Log.i(TAG, "zoom model- touch event: UP")
                mode = Mode.NONE
                prevDx = dx
                prevDy = dy
            }
        }
        scaleDetector!!.onTouchEvent(motionEvent)
        if (mode == Mode.DRAG && scale >= MIN_ZOOM || mode == Mode.ZOOM) {
            parent.requestDisallowInterceptTouchEvent(true)
            val maxDx =
                (child().width - child().width / scale) / 2 * scale
            val maxDy =
                (child().height - child().height / scale) / 2 * scale
            dx = Math.min(Math.max(dx, -maxDx), maxDx)
            dy = Math.min(Math.max(dy, -maxDy), maxDy)
            Log.i(
                TAG,
                "Width: " + child().width + ", scale " + scale + ", dx " + dx
                        + ", max " + maxDx
            )
            applyScaleAndTranslation()
        }
        return true
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        onTouchEvent(ev)
        return false // true consumes here false passes to child
    }

    // ScaleGestureDetector
    override fun onScaleBegin(scaleDetector: ScaleGestureDetector): Boolean {
        Log.i(TAG, "onScaleBegin")
        return true
    }

    override fun onScale(scaleDetector: ScaleGestureDetector): Boolean {
        val scaleFactor = scaleDetector.scaleFactor
        Log.i(TAG, "onScale$scaleFactor")
        if (lastScaleFactor == 0f || Math.signum(scaleFactor) == Math.signum(
                lastScaleFactor
            )
        ) {
            scale *= scaleFactor
            scale = Math.max(
                MIN_ZOOM,
                Math.min(scale, MAX_ZOOM)
            )
            lastScaleFactor = scaleFactor
            Log.i(TAG, "scale zoom")
        } else {
            lastScaleFactor = 0f
            Log.i(TAG, "scales = 0")
        }
        return true
    }

    override fun onScaleEnd(scaleDetector: ScaleGestureDetector) {
        Log.i(TAG, "onScaleEnd")
        mode = Mode.NONE
    }

    private fun applyScaleAndTranslation() {
        child().scaleX = scale
        child().scaleY = scale
        child().translationX = dx
        child().translationY = dy
    }

    private fun child(): View {
        return getChildAt(0)
    }

    companion object {
        private const val TAG = "ZoomLayout"
        private const val MIN_ZOOM = 1.0f
        private const val MAX_ZOOM = 4.0f
    }
}