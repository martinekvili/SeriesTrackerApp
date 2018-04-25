package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event;

import hu.bme.aut.mobsoftlab.seriestrackerapp.util.ObjectsHelper;

public class NetworkErrorEvent {

    private final String errorMessage;

    public NetworkErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    // region equals implementation

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkErrorEvent that = (NetworkErrorEvent) o;
        return ObjectsHelper.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return ObjectsHelper.hash(errorMessage);
    }

    // endregion
}
