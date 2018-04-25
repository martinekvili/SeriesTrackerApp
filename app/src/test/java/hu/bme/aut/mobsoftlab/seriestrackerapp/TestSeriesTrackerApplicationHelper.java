package hu.bme.aut.mobsoftlab.seriestrackerapp;

import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.TestDatabaseModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.TestEventSenderModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.IEventSender;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.IOmdbClient;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.TestNetworkModule;

public class TestSeriesTrackerApplicationHelper {

    public static void createTestInjector(InteractorModule interactorModule) {
        SeriesTrackerApplication.injector = DaggerSeriesTrackerApplicationComponent
                .builder()
                .databaseModule(new TestDatabaseModule(null))
                .networkModule(new TestNetworkModule(null))
                .interactorModule(interactorModule)
                .build();
    }

    public static void createTestInjector(IEventSender eventSender, IOmdbClient apiClient, ISavedSeriesDAL dal) {
        SeriesTrackerApplication.injector = DaggerSeriesTrackerApplicationComponent
                .builder()
                .eventSenderModule(new TestEventSenderModule(eventSender))
                .networkModule(new TestNetworkModule(apiClient))
                .databaseModule(new TestDatabaseModule(dal))
                .build();
    }
}
