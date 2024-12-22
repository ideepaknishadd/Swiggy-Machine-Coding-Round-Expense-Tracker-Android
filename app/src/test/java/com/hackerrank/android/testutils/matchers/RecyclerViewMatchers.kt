package com.hackerrank.android.testutils.matchers

import android.os.Looper
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.robolectric.Shadows

fun hasItemCount(count: Int): Matcher<View> = RecyclerViewItemCountMatcher(count)

private class RecyclerViewItemCountMatcher(
    private val count: Int
) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun describeTo(description: Description) {
        description.appendText("has $count items")
    }

    override fun matchesSafely(item: RecyclerView): Boolean {
        return item.adapter?.itemCount == count
    }

}

fun viewHolderChildMatches(
    position: Int,
    @IdRes id: Int,
    matcher: Matcher<View>
): BoundedMatcher<View, RecyclerView> =
    RecyclerViewItemMatcher<View>(position, id, matcher)

private class RecyclerViewItemMatcher<T : View>(
    private val position: Int,
    private val id: Int,
    private val matcher: Matcher<View>
) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
    override fun matchesSafely(view: RecyclerView): Boolean {
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        view.scrollToPosition(position)
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val itemView = view.findViewHolderForAdapterPosition(position)!!.itemView
        val targetView = itemView.findViewById<T>(id)
        return matcher.matches(targetView)
    }

    override fun describeTo(description: Description) {
        description.appendText("View $id has matcher:")
        matcher.describeTo(description)
    }
}