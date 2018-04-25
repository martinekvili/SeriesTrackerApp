package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

import javax.inject.Singleton;

import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.IAboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.DetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.MainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.NewSeriesInteractor;

public class MockInteractorModule extends InteractorModule {

    private final IAboutInteractor aboutInteractor;

    public MockInteractorModule(IAboutInteractor aboutInteractor) {
        this.aboutInteractor = aboutInteractor;
    }

    @Provides
    @Singleton
    @Override
    public MainInteractor provideMainInteractor() {
        return new MainInteractor();
    }

    @Provides
    @Singleton
    @Override
    public IAboutInteractor provideAboutInteractor() {
        return aboutInteractor;
    }

    @Provides
    @Singleton
    @Override
    public DetailsInteractor provideDetailsInteractor() {
        return new DetailsInteractor();
    }

    @Provides
    @Singleton
    @Override
    public NewSeriesInteractor providesNewSeriesInteractor() {
        return new NewSeriesInteractor();
    }
}
