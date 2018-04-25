package hu.bme.aut.mobsoftlab.seriestrackerapp;

import hu.bme.aut.mobsoftlab.seriestrackerapp.database.TestDatabaseModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.TestNetworkModule;

public class TestSeriesTrackerApplicationHelper {

    public static void createTestInjector(InteractorModule interactorModule) {
        SeriesTrackerApplication.injector = DaggerSeriesTrackerApplicationComponent
                .builder()
                .databaseModule(new TestDatabaseModule())
                .networkModule(new TestNetworkModule())
                .interactorModule(interactorModule)
                .build();
    }
}
