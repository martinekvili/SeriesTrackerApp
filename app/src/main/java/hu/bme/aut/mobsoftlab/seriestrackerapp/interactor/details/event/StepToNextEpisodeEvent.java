package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.util.ObjectsHelper;

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

    // region equals implementation

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepToNextEpisodeEvent that = (StepToNextEpisodeEvent) o;
        return ObjectsHelper.equals(series, that.series) &&
                ObjectsHelper.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return ObjectsHelper.hash(series, details);
    }


    // endregion
}
