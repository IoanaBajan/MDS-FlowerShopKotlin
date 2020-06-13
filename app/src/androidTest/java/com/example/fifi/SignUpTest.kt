package com.example.fifi

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.fifi.activities.SignUpActivity
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class SignUpTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<SignUpActivity> = ActivityTestRule(
        SignUpActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.test", appContext.packageName)
    }


    @Test
    fun correctData() {
        Espresso.onView(ViewMatchers.withId(R.id.idFirstName)).perform(ViewActions.scrollTo(),ViewActions.typeText("Alina"))
        Espresso.onView(ViewMatchers.withId(R.id.idLastName)).perform(ViewActions.scrollTo(),ViewActions.typeText("Mocanu"))
        Espresso.onView(ViewMatchers.withId(R.id.idUsernameR)).perform(ViewActions.scrollTo(),ViewActions.typeText("alinabulina"))
        Espresso.onView(ViewMatchers.withId(R.id.idPasswordR)).perform(ViewActions.scrollTo(),ViewActions.typeText("parola"))
        Espresso.onView(ViewMatchers.withId(R.id.idConfirmPasswordR)).perform(ViewActions.scrollTo(),ViewActions.typeText("parola"))
        Espresso.onView(ViewMatchers.withId(R.id.idEmail)).perform(ViewActions.scrollTo(),ViewActions.typeText("alinam@gmail.com"))

        Espresso.onView(ViewMatchers.withId(R.id.idRegister))
            .perform(ViewActions.scrollTo(),ViewActions.click());

        Espresso.onView(ViewMatchers.withText("Succes!"))
            .inRoot(RootMatchers.withDecorView(Matchers.not(rule.getActivity().getWindow().getDecorView())))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

    }
}