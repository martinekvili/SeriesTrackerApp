package hu.bme.aut.mobsoftlab.seriestrackerapp;

import android.app.Application;

public class SeriesTrackerApplication extends Application {

    public static SeriesTrackerApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerSeriesTrackerApplicationComponent.builder().build();
    }
}
