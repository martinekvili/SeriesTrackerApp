package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event;

import java.util.Set;

public class GetAlreadyAddedSeriesIDsEvent {

    private final Set<String> alreadyAddedSeriesIDs;

    public GetAlreadyAddedSeriesIDsEvent(Set<String> alreadyAddedSeriesIDs) {
        this.alreadyAddedSeriesIDs = alreadyAddedSeriesIDs;
    }

    public Set<String> getAlreadyAddedSeriesIDs() {
        return alreadyAddedSeriesIDs;
    }
}
