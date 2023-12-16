package com.example.mytestapplication.mitch

import android.view.View
import android.view.Window
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.Espresso.setFailureHandler
import androidx.test.espresso.FailureHandler
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.mytestapplication.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get:Rule
    var mActivityRule: ActivityScenarioRule<MainActivity?>? = ActivityScenarioRule(
        MainActivity::class.java
    )


    /*
    * check MainActivity is opened or not
    * */

    @Test
    fun test_isActivityInView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_title_nextButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnNext)).check(matches(isDisplayed()))  // method 1
        onView(withId(R.id.main_title)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))) // method 2
    }

    @Test
    fun test_isTitleTextDisplayed() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main_title)).check(matches(withText(R.string.main_activity)))
    }

    @Test
    fun test_navSecondaryActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnNext)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }

    @Test
    fun test_backPress_toMainActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.btnNext)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
        // onView(withId(R.id.btnBack)).perform(click())
        pressBack()
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun test_clickForRecyclerView() {
        var view: Window? = null
        val activity = mActivityRule?.scenario?.onActivity {
            view = it?.window
        }

        Espresso.onView(ViewMatchers.withId(R.id.recList)).inRoot(
            RootMatchers.withDecorView(
                Matchers.`is`(view?.decorView)
            )
        ).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
    }

    @Test
    fun test_CaseRecyclerViewScroll() {
        var view: Window? = null
        val activity = mActivityRule?.scenario?.onActivity {
            view = it?.window
        }

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recList)
        val count = recyclerView?.adapter?.itemCount
        Espresso.onView(ViewMatchers.withId(R.id.recList))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(view?.decorView)
                )
            ).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    count?.minus(1) ?: 0
                )
            )
    }

    @Test
    fun test_CaseRecyclerViewItemVisible() {
        var view: Window? = null
        val activity = mActivityRule?.scenario?.onActivity {
            view = it?.window
        }

        /*
        * Önce son elemana git.
        * */

        /*val recyclerView = view?.findViewById<RecyclerView>(R.id.recList)
        val count = recyclerView?.adapter?.itemCount
        Espresso.onView(ViewMatchers.withId(R.id.recList))
            .inRoot(
                RootMatchers.withDecorView(
                    Matchers.`is`(view?.decorView)
                )
            ).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    count?.minus(1) ?: 0
                )
            )*/
        onView(withId(R.id.recList))
            .check(matches(withViewAtPosition(16, hasDescendant(allOf(withId(R.id.tvData), isDisplayed())))))

    }

    private fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description?) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

}