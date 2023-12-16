package com.example.mytestapplication.mitch

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mytestapplication.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SecondaryActivityTest {

    @Test
    fun test_isActivityInView() {
        val activityScenario = ActivityScenario.launch(SecondaryActivity::class.java)
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_title_nextButton() {
        val activityScenario = ActivityScenario.launch(SecondaryActivity::class.java)
        onView(withId(R.id.btnBack)).check(matches(isDisplayed()))  // method 1
        onView(withId(R.id.secondary_title)).check(matches(
            ViewMatchers.withEffectiveVisibility(
                ViewMatchers.Visibility.VISIBLE
            )
        )) // method 2
    }

    @Test
    fun test_isTitleTextDisplayed() {
        val activityScenario = ActivityScenario.launch(SecondaryActivity::class.java)
        onView(withId(R.id.secondary_title)).check(matches(ViewMatchers.withText(R.string.secondary_activity)))
    }

}