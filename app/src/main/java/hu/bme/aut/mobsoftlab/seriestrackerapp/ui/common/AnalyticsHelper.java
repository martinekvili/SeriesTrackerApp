package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.common;

import android.app.Activity;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;

public class AnalyticsHelper {

    public static Tracker getTracker(Activity activity) {
        // Obtain the shared Tracker instance.
        SeriesTrackerApplication application = (SeriesTrackerApplication) activity.getApplication();
        return application.getDefaultTracker();
    }

    public static void sendScreenViewEvent(Tracker mTracker, String screenName) {
        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
