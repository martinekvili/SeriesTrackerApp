package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.IEventSender;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetAlreadyAddedSeriesIDsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;

public class MainInteractor implements IMainInteractor {

    @Inject
    IEventSender eventSender;

    @Inject
    ISavedSeriesDAL savedSeriesDAL;

    public MainInteractor() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    public void getSeriesList() {
        eventSender.send(new GetSeriesListEvent(savedSeriesDAL.getSavedSeries()));
    }

    @Override
    public void getAlreadyAddedSeriesIDs() {
        eventSender.send(new GetAlreadyAddedSeriesIDsEvent(savedSeriesDAL.getAlreadyAddedSeriesIDs()));
    }
}
