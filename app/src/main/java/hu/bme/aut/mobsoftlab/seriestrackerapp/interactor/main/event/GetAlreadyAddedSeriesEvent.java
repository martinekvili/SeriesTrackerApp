package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event;

import java.util.Set;

public class GetAlreadyAddedSeriesEvent {

    private final Set<String> alreadyAddedSeries;

    public GetAlreadyAddedSeriesEvent(Set<String> alreadyAddedSeries) {
        this.alreadyAddedSeries = alreadyAddedSeries;
    }

    public Set<String> getAlreadyAddedSeries() {
        return alreadyAddedSeries;
    }
}
