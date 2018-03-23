package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class StepToNextEpisodeEvent {

    private final SavedSeries series;
    private final EpisodeDetails details;

    public StepToNextEpisodeEvent(SavedSeries series, EpisodeDetails details) {
        this.series = series;
        this.details = details;
    }

    public SavedSeries getSeries() {
        return series;
    }

    public EpisodeDetails getDetails() {
        return details;
    }
}
