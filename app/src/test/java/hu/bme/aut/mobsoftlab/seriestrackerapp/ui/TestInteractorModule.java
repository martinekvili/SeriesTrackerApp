package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

import javax.inject.Singleton;

import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.IAboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.IDetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.MainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.NewSeriesInteractor;

public class TestInteractorModule extends InteractorModule {

    private final IAboutInteractor aboutInteractor;
    private final IDetailsInteractor detailsInteractor;

    public TestInteractorModule(IAboutInteractor aboutInteractor) {
        this.aboutInteractor = aboutInteractor;
        this.detailsInteractor = null;
    }

    public TestInteractorModule(IDetailsInteractor detailsInteractor) {
        this.aboutInteractor = null;
        this.detailsInteractor = detailsInteractor;
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
    public IDetailsInteractor provideDetailsInteractor() {
        return detailsInteractor;
    }

    @Provides
    @Singleton
    @Override
    public NewSeriesInteractor provideNewSeriesInteractor() {
        return new NewSeriesInteractor();
    }
}
