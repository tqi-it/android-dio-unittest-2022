package io.github.gustavobarbosab.movies

import android.content.Intent
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
class MoviesTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)


    private lateinit var robot: MoviesRobot

    @Before
    fun setUp() {
        robot = MoviesRobot(activityRule)
    }

    @After
    fun finish() = robot.finish()

    @Test
    fun checkToolbarTitleAndTitleFilm() {
        robot.run()
            .checkTextIsDisplayed("Detalhes")
            .checkTextIsDisplayed("Pil filme")
    }



}


