package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.util.ObjectsHelper;

public class GetSeriesDetailsEvent {

    private final EpisodeDetails details;

    public GetSeriesDetailsEvent(EpisodeDetails details) {
        this.details = details;
    }

    public EpisodeDetails getDetails() {
        return details;
    }

    // region equals implementation

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetSeriesDetailsEvent that = (GetSeriesDetailsEvent) o;
        return ObjectsHelper.equals(details, that.details);
    }

    @Override
    public int hashCode() {
        return ObjectsHelper.hash(details);
    }

    // endregion
}
