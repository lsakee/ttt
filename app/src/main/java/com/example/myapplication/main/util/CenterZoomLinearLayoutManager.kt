package com.example.myapplication.main.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class CenterZoomLinearLayoutManager(
    context: Context,
    private val mShrinkDistance: Float = 0.95f,
    private val mShrinkAmount: Float = 0.13f
) : LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleChildren()
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return if (orientation == HORIZONTAL) {
            super.scrollHorizontallyBy(dx, recycler, state).also { scaleChildren() }
        } else {
            0
        }
    }

    private fun scaleChildren() {
        val midpoint = width / 2f
        val d1 = mShrinkDistance * midpoint
        for (i in 0 until childCount) {
            val child = getChildAt(i) as View
            val d = Math.min(d1, Math.abs(midpoint - (getDecoratedRight(child) + getDecoratedLeft(child)) / 2f))
            val scale = 1f - mShrinkAmount * d / d1
            child.scaleX = scale
            child.scaleY = scale

            val viewShadow = child.findViewById<View>(R.id.view_shadow)
            viewShadow.visibility = if(scale in 0.9f .. 1.1f) View.GONE else View.VISIBLE
        }
    }
}