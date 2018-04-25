package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor;

import org.mockito.Mockito;

import hu.bme.aut.mobsoftlab.seriestrackerapp.TestSeriesTrackerApplicationHelper;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.IEventSender;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.IOmdbClient;

public class InteractorTestBase<T> {

    protected IEventSender eventSender;
    protected IOmdbClient apiClient;
    protected ISavedSeriesDAL dal;

    protected T interactor;

    protected void initMock(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        eventSender = Mockito.mock(IEventSender.class);
        apiClient = Mockito.mock(IOmdbClient.class);
        dal = Mockito.mock(ISavedSeriesDAL.class);

        TestSeriesTrackerApplicationHelper.createTestInjector(eventSender, apiClient, dal);

        interactor = clazz.newInstance();
    }
}
