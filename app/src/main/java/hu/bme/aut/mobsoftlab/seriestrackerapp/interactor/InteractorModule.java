package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.AboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.DetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.MainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.NewSeriesInteractor;

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

    @Provides
    public DetailsInteractor provideDetailsInteractor() {
        return new DetailsInteractor();
    }

    @Provides
    public NewSeriesInteractor providesNewSeriesInteractor() {
        return new NewSeriesInteractor();
    }
}
