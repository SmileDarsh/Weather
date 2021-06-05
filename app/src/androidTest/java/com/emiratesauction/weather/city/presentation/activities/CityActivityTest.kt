package com.emiratesauction.weather.city.presentation.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.emiratesauction.weather.R
import com.emiratesauction.weather.city.presentation.activities.UITestUtils.withRecyclerView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CityActivityTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<CityActivity> =
        ActivityScenarioRule(CityActivity::class.java)

    lateinit var cityActivity: CityActivity

    @Before
    fun setup() {
        activityRule.scenario.onActivity { activity ->
            cityActivity = activity
        }
    }

    @Test
    fun searchForCairoCity() {
        val stringToBeTyped = "Cairo"
        onView(withId(R.id.etSearch))
            .perform(
                typeText(stringToBeTyped),
                ViewActions.pressImeActionButton(),
                closeSoftKeyboard()
            )

        Thread.sleep(2000)

        onView(withRecyclerView(R.id.rvCity).atPositionOnView(0, R.id.tvCity))
            .check(matches(withText("Cairo")))

    }
}