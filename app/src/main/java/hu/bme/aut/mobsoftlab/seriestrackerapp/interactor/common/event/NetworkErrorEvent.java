package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event;

public class NetworkErrorEvent {

    private final String errorMessage;

    public NetworkErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
