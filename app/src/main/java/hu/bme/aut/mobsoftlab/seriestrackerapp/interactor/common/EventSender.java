package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common;

import org.greenrobot.eventbus.EventBus;

public class EventSender implements IEventSender {
    @Override
    public <T> void send(T event) {
        EventBus.getDefault().post(event);
    }
}
