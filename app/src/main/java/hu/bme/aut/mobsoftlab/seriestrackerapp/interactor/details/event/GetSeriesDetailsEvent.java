package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;

public class GetSeriesDetailsEvent {

    private final EpisodeDetails details;

    public GetSeriesDetailsEvent(EpisodeDetails details) {
        this.details = details;
    }

    public EpisodeDetails getDetails() {
        return details;
    }
}
