package com.example.mordowiciel.filmappupgraded.ui.home.adapter;


import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridLayoutItemDecorator extends RecyclerView.ItemDecoration {

    private int leftSpace;
    private int rightSpace;
    private int bottomSpace;
    private int topSpace;
    private int columnSpan;

    public GridLayoutItemDecorator(int leftSpace, int rightSpace, int bottomSpace, int topSpace, int columnSpan) {
        this.leftSpace = leftSpace;
        this.rightSpace = rightSpace;
        this.bottomSpace = bottomSpace;
        this.topSpace = topSpace;
        this.columnSpan = columnSpan;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = leftSpace;
        outRect.right = rightSpace;
        outRect.bottom = bottomSpace;

        if (parent.getChildLayoutPosition(view) < columnSpan) {
            outRect.top = topSpace;
        } else {
            outRect.top = 0;
        }
    }
}
