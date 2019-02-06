
package com.haris.android.weather.test.view.activity;

import android.app.Fragment;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.haris.android.weather.presentation.R;
import com.haris.android.weather.presentation.view.activity.WeatherActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class WeatherDetailsActivityTest extends ActivityInstrumentationTestCase2<WeatherActivity> {


    private WeatherActivity weatherDetailsActivity;

    public WeatherDetailsActivityTest() {
        super(WeatherActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        this.setActivityIntent(createTargetIntent());
        this.weatherDetailsActivity = getActivity();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContainsWeatherDetailsFragment() {
        Fragment weatherDetailsFragment =
                weatherDetailsActivity.getFragmentManager().findFragmentById(R.id.fragmentContainer);
        assertThat(weatherDetailsFragment, is(notNullValue()));
    }

    public void testContainsProperTitle() {
        String actualTitle = this.weatherDetailsActivity.getTitle().toString().trim();

        assertThat(actualTitle, is("Weather Details"));
    }

    public void testLoadWeatherHappyCaseViews() {
        onView(withId(R.id.rl_retry)).check(matches(not(isDisplayed())));
        onView(withId(R.id.rl_progress)).check(matches(not(isDisplayed())));

        onView(withId(R.id.weatherTemperature)).check(matches(isDisplayed()));
        onView(withId(R.id.weatherTime)).check(matches(isDisplayed()));
        onView(withId(R.id.weatherLocation)).check(matches(isDisplayed()));
    }


    private Intent createTargetIntent() {
        Intent intentLaunchActivity =
                WeatherActivity.getCallingIntent(getInstrumentation().getTargetContext());

        return intentLaunchActivity;
    }
}
