package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event;

import java.util.Set;

import hu.bme.aut.mobsoftlab.seriestrackerapp.util.ObjectsHelper;

public class GetAlreadyAddedSeriesIDsEvent {

    private final Set<String> alreadyAddedSeriesIDs;

    public GetAlreadyAddedSeriesIDsEvent(Set<String> alreadyAddedSeriesIDs) {
        this.alreadyAddedSeriesIDs = alreadyAddedSeriesIDs;
    }

    public Set<String> getAlreadyAddedSeriesIDs() {
        return alreadyAddedSeriesIDs;
    }

    // region equals implementation

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAlreadyAddedSeriesIDsEvent that = (GetAlreadyAddedSeriesIDsEvent) o;
        return ObjectsHelper.equals(alreadyAddedSeriesIDs, that.alreadyAddedSeriesIDs);
    }

    @Override
    public int hashCode() {
        return ObjectsHelper.hash(alreadyAddedSeriesIDs);
    }


    // endregion
}
