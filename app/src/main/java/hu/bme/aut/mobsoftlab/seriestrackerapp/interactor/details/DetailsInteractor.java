package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details;

import org.greenrobot.eventbus.EventBus;

import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.GetSeriesDetailsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesDetails;

public class DetailsInteractor {

    public void getSeriesDetails() {
        // TODO
        EventBus.getDefault().post(new GetSeriesDetailsEvent(new SeriesDetails()));
    }
}
