package google.louco.com.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import google.louco.com.bakingapp.ui.activity.BakingActivity;
import google.louco.com.bakingapp.ui.activity.MainActivity;
import android.support.test.rule.ActivityTestRule;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String BakingName = "Brownies";

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void LoadRV(){
        onView(withId(R.id.rv_list_recipes))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(withId(R.id.tb_baking)).check(matches(withText(BakingName)));
        //onData().inAdapterView(withId(R.id.rv_list_recipes)).atPosition(1).perform(click());
        //onView(new RecyclerViewMatcher())
    }
}
