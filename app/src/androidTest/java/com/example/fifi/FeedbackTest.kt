package com.example.fifi

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.fifi.activities.ProfileActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FeedbackTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<ProfileActivity> = ActivityTestRule(
        ProfileActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.test", appContext.packageName)
    }

    @Test
    fun feedbackTest() {
        Espresso.onView(ViewMatchers.withId(R.id.feedbackButton))
            .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.feedbackText)).perform(ViewActions.typeText("Cea mai smechera aplicatie !"))
        Espresso.onView(ViewMatchers.withId(R.id.sendFeedbackButton))
            .perform(ViewActions.scrollTo(), ViewActions.click());

    }
}