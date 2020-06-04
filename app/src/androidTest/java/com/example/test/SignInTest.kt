package com.example.test

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.not
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.test", appContext.packageName)
    }

    @Test
    fun login() {
        Espresso.onView(withId(R.id.idUsername)).perform(typeText("bety"))
        Espresso.onView(withId(R.id.idPassword)).perform(typeText("bauu"))
        Espresso.onView(withId(R.id.idPassword)).perform(closeSoftKeyboard());
        Espresso.onView(withId(R.id.idSignIn))
            .perform(ViewActions.click());

        Espresso.onView(withText("Succes login!"))
            .inRoot(withDecorView(not(rule.getActivity().getWindow().getDecorView())))
            .check(matches(isDisplayed()));
    }
}