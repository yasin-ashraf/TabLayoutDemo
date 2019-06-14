package com.yasin.tablayoutdemo;

import android.view.View;
import android.view.ViewParent;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Px;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

public final class PageMarginTransformer implements ViewPager2.PageTransformer {
    private final int mMarginPx;

    /**
     * Creates a {@link PageMarginTransformer}.
     *
     * @param marginPx non-negative margin
     */
    public PageMarginTransformer(@Px int marginPx) {
        Preconditions.checkArgumentNonnegative(marginPx, "Margin must be non-negative");
        mMarginPx = marginPx;
    }

    @Override
    public void transformPage(@NonNull View page,
                              @FloatRange(from = -1.0, to = 1.0) float position) {
        Preconditions.checkState(position >= -1f && position <= 1f,
                "Expected position values between -1 and 1.");

        ViewPager2 viewPager = requireViewPager(page);

        float offset = mMarginPx * position;

        if (viewPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
            page.setTranslationX(offset);
        } else {
            page.setTranslationY(offset);
        }
    }

    private ViewPager2 requireViewPager(@NonNull View page) {
        ViewParent parent = page.getParent();
        ViewParent parentParent = parent.getParent();

        if (parent instanceof RecyclerView && parentParent instanceof ViewPager2) {
            return (ViewPager2) parentParent;
        }

        throw new IllegalStateException(
                "Expected the page view to be managed by a ViewPager2 instance.");
    }
}
