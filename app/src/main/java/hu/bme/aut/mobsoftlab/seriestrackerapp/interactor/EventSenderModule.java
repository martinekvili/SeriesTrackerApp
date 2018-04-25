package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.EventSender;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.IEventSender;

@Module
public class EventSenderModule {

    @Provides
    public IEventSender provideEventSender() {
        return new EventSender();
    }
}
