package com.tingting.ver01.teamInfo

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class TeamInfoRecyclerViewMargin(private val spaceWidth:Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State) {
        with(outRect) {
            right = spaceWidth -5
            top = spaceWidth +5
        }

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.right = spaceWidth -5
            outRect.top = spaceWidth +5
        }
     else {
         outRect.top = spaceWidth +5
        }
    }
}