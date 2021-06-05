package com.emiratesauction.weather.city.presentation.activities

object UITestUtils {
    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }
}