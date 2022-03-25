package io.github.gustavobarbosab

import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import io.github.gustavobarbosab.movies.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@MediumTest
class MoviesDetailsTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java, false, false)
    
    private lateinit var robot: MoviesDetailsRobot

    @Before
    fun setUp() {
        robot = MoviesDetailsRobot(activityRule)
    }

    @After
    fun finish() = robot.finish()

    @Test
    fun checkToolbarTitleAndTitleFilm() {
        robot.run()
            .checkTextIsDisplayed("Detalhes")
            .checkTextIsDisplayed("Pil filme")
            .clickSaveFavorite()
            .checkSnackBar()
            .clickSaveFavorite()
    }



}


