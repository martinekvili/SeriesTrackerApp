package hu.bme.aut.mobsoftlab.seriestrackerapp;

import com.crashlytics.android.core.CrashlyticsCore;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.orm.SugarApp;

import io.fabric.sdk.android.Fabric;

public class SeriesTrackerApplication extends SugarApp {

    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    public static SeriesTrackerApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this,
                new CrashlyticsCore.Builder()
                        .disabled(!BuildConfig.FLAVOR.equals("prod"))
                        .build());
        sAnalytics = GoogleAnalytics.getInstance(this);
        injector = DaggerSeriesTrackerApplicationComponent.builder().build();
    }

    /**
     * Gets the default {@link Tracker} for this {@link SeriesTrackerApplication}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (sTracker == null)
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);

        return sTracker;
    }
}
