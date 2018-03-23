package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.AboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.MainInteractor;

@Module
public class InteractorModule {

    @Provides
    public MainInteractor provideMainInteractor() {
        return new MainInteractor();
    }

    @Provides
    public AboutInteractor provideAboutInteractor() {
        return new AboutInteractor();
    }
}
