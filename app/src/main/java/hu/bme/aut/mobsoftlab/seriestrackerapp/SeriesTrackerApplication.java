package hu.bme.aut.mobsoftlab.seriestrackerapp;

import com.orm.SugarApp;

public class SeriesTrackerApplication extends SugarApp {

    public static SeriesTrackerApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerSeriesTrackerApplicationComponent.builder().build();
    }
}
