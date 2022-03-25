package io.github.gustavobarbosab.movies

import android.content.Intent
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.google.android.material.bottomnavigation.BottomNavigationView
import junit.framework.AssertionFailedError
import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import java.util.concurrent.TimeoutException

class MoviesRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>
) {

    private var activity: MainActivity? = null

    fun run() = apply {
        activity = activityTestRule.launchActivity(Intent())
    }

    fun checkTextIsDisplayed(text: String) = apply {
        onView(ViewMatchers.withText(text))
            .check(matches(isDisplayed()))
    }

    private fun withViewInsideContainer(
        elementMatcher: Matcher<View>,
        containerMatcher: Matcher<View>
    ): Matcher<View> =
        CoreMatchers.allOf(elementMatcher, ViewMatchers.isDescendantOfA(containerMatcher))

    fun finish() {
        activityTestRule.finishActivity()
        activity = null
    }

}