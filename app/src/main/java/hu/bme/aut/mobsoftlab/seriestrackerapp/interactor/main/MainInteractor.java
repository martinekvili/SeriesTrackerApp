package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.IEventSender;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetAlreadyAddedSeriesIDsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;

public class MainInteractor {

    @Inject
    IEventSender eventSender;

    @Inject
    ISavedSeriesDAL savedSeriesDAL;

    public MainInteractor() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public void getSeriesList() {
        eventSender.send(new GetSeriesListEvent(savedSeriesDAL.getSavedSeries()));
    }

    public void getAlreadyAddedSeriesIDs() {
        eventSender.send(new GetAlreadyAddedSeriesIDsEvent(savedSeriesDAL.getAlreadyAddedSeriesIDs()));
    }
}
