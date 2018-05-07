package hu.bme.aut.mobsoftlab.seriestrackerapp;

import com.crashlytics.android.core.CrashlyticsCore;
import com.orm.SugarApp;

import io.fabric.sdk.android.Fabric;

public class SeriesTrackerApplication extends SugarApp {

    public static SeriesTrackerApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this,
                new CrashlyticsCore.Builder()
                        .disabled(!BuildConfig.FLAVOR.equals("prod"))
                        .build());

        injector = DaggerSeriesTrackerApplicationComponent.builder().build();
    }
}
