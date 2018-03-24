package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class MainInteractor {

    public void getSeriesList() {
        // TODO read saved series from database
        EventBus.getDefault().post(new GetSeriesListEvent(new ArrayList<>()));
    }

    public void addNewSeries(SavedSeries series) {
        // TODO save new series to database
    }
}
