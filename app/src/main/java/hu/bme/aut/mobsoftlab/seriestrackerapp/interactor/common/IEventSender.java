package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common;

public interface IEventSender {
    <T> void send(T event);
}
