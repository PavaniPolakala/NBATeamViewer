package com.example.nbateamviewer;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.nbateamviewer.view.ui.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    public final ActivityTestRule<MainActivity> myActivityTestRule = new ActivityTestRule<>(MainActivity.class, false, false);
    private static final Intent MY_ACTIVITY_INTENT = new Intent(InstrumentationRegistry.getTargetContext(), MainActivity.class);

    @Before
    public void setup() {
        myActivityTestRule.launchActivity(MY_ACTIVITY_INTENT);
    }

    @Test
    public void isFragVisibleTest(){
        onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));
    }
    @Test
    public void itemClickTest() {
        onView(withId(R.id.teams_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.action_name)).check(doesNotExist());
    }

    @Test
    public void sortByNameClickTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withId(R.id.action_name)).perform(click());
        onView(withId(R.id.team_full_name)).check(matches(withText("Atlanta Hawks")));
    }

    @Test
    public void sortByWinsClickTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withId(R.id.action_wins)).perform(click());
        onView(withId(R.id.wins)).check(matches(withText("18")));
    }

    @Test
    public void sortByLossesClickTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withId(R.id.action_losses)).perform(click());
        onView(withId(R.id.losses)).check(matches(withText("13")));
    }
}
