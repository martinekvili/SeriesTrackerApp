package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesDetails;

public class GetSeriesDetailsEvent {

    private final SeriesDetails details;

    public GetSeriesDetailsEvent(SeriesDetails details) {
        this.details = details;
    }

    public SeriesDetails getDetails() {
        return details;
    }
}
