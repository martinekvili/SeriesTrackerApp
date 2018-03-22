package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event;

import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class GetSeriesListEvent {

    private final List<SavedSeries> series;

    public GetSeriesListEvent(List<SavedSeries> series) {
        this.series = series;
    }

    public List<SavedSeries> getSeries() {
        return series;
    }
}
