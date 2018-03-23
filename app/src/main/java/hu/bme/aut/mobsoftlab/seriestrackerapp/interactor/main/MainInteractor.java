package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;

import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetAlreadyAddedSeriesEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;

public class MainInteractor {

    public void getSeriesList() {
        // TODO read saved series from database
        EventBus.getDefault().post(new GetSeriesListEvent(new ArrayList<>()));
    }

    public void getAlreadyAddedSeries() {
        // TODO read saved series' ids from database
        EventBus.getDefault().post(new GetAlreadyAddedSeriesEvent(new HashSet<>()));
    }
}
