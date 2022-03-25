package io.github.gustavobarbosab

import android.content.Intent
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.google.android.material.snackbar.Snackbar
import io.github.gustavobarbosab.movies.R
import io.github.gustavobarbosab.movies.MainActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class MoviesDetailsRobot(
    private val activityTestRule: ActivityTestRule<MainActivity>
) {

    private var activity: MainActivity? = null

    fun run() = apply {
        activity = activityTestRule.launchActivity(Intent())
    }

    fun checkTextIsDisplayed(text: String) = apply {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    fun clickSaveFavorite() = apply {
        onView(withId(io.github.gustavobarbosab.commons.R.id.movie_fab)).perform(click());
    }

    fun checkSnackBar() = apply {
        withSnackBarText("Filme adicionado aos favoritos")
    }

    private  fun withSnackBarText(text: String): Matcher<View> = allOf(
        withText(text),
        ViewMatchers.isDescendantOfA(ViewMatchers.isAssignableFrom(Snackbar.SnackbarLayout::class.java))
    )

    fun finish() {
        activityTestRule.finishActivity()
        activity = null
    }

}