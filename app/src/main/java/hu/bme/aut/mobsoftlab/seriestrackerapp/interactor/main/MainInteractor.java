package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetAlreadyAddedSeriesIDsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;

public class MainInteractor {

    @Inject
    ISavedSeriesDAL savedSeriesDAL;

    public MainInteractor() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public void getSeriesList() {
        EventBus.getDefault().post(new GetSeriesListEvent(savedSeriesDAL.getSavedSeries()));
    }

    public void getAlreadyAddedSeriesIDs() {
        EventBus.getDefault().post(new GetAlreadyAddedSeriesIDsEvent(savedSeriesDAL.getAlreadyAddedSeriesIDs()));
    }
}
