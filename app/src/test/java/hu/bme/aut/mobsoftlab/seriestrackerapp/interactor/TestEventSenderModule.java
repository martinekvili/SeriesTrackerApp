package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.IEventSender;

@Module
public class TestEventSenderModule extends EventSenderModule {

    private final IEventSender eventSender;

    public TestEventSenderModule(IEventSender eventSender) {
        this.eventSender = eventSender;
    }

    @Provides
    @Override
    public IEventSender provideEventSender() {
        return eventSender;
    }
}
