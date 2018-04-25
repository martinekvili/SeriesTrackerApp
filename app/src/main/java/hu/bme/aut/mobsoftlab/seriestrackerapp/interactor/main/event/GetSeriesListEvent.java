package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event;

import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.util.ObjectsHelper;

public class GetSeriesListEvent {

    private final List<SavedSeries> series;

    public GetSeriesListEvent(List<SavedSeries> series) {
        this.series = series;
    }

    public List<SavedSeries> getSeries() {
        return series;
    }

    // region equals implementation

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetSeriesListEvent that = (GetSeriesListEvent) o;
        return ObjectsHelper.equals(series, that.series);
    }

    @Override
    public int hashCode() {
        return ObjectsHelper.hash(series);
    }


    // endregion
}
