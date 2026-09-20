package com.infynova.launcher.overlay

import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.TextView
import com.infynova.launcher.R

/**
 * DynamicIslandOverlay — Morphs system status bar area into an interactive HUD.
 */
class DynamicIslandOverlay(private val context: Context) {

    private val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private var islandView: FrameLayout? = null

    enum class IslandState { IDLE, COMPACT, EXPANDED }

    fun show(state: IslandState = IslandState.COMPACT) {
        if (islandView != null) return

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            120, // Initial height
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
            y = 20 // Margin from top
        }

        islandView = FrameLayout(context).apply {
            setBackgroundResource(R.drawable.dynamic_island_bg)
            setPadding(32, 8, 32, 8)
            
            val text = TextView(context).apply {
                text = "Nova Intelligence Active"
                setTextColor(0xFFFFFFFF.toInt())
                textSize = 12f
            }
            addView(text)
        }

        windowManager.addView(islandView, params)
    }

    fun hide() {
        islandView?.let {
            windowManager.removeView(it)
            islandView = null
        }
    }
}
