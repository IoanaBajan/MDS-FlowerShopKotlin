package com.example.test

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BouquetTest {
    @Rule
    @JvmField
    val rule: ActivityTestRule<BouquetActivity> = ActivityTestRule(BouquetActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.test", appContext.packageName)
    }

    @Test
    fun bouquet(){
        Espresso.onView(ViewMatchers.withId(R.id.buttonRoses))
            .perform( ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonLilies))
            .perform(ViewActions.scrollTo(),ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonOrnament2))
            .perform(ViewActions.scrollTo(),ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonWrap5))
            .perform(ViewActions.scrollTo(),ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonSubmit))
            .perform(ViewActions.scrollTo(),ViewActions.click());

    }
}