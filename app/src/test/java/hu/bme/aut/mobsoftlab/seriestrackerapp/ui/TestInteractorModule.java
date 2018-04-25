package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

import javax.inject.Singleton;

import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.IAboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.IDetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.IMainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.MainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.NewSeriesInteractor;

public class TestInteractorModule extends InteractorModule {

    private final IMainInteractor mainInteractor;
    private final IAboutInteractor aboutInteractor;
    private final IDetailsInteractor detailsInteractor;

    public TestInteractorModule(IMainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
        this.aboutInteractor = null;
        this.detailsInteractor = null;
    }

    public TestInteractorModule(IAboutInteractor aboutInteractor) {
        this.mainInteractor = null;
        this.aboutInteractor = aboutInteractor;
        this.detailsInteractor = null;
    }

    public TestInteractorModule(IDetailsInteractor detailsInteractor) {
        this.mainInteractor = null;
        this.aboutInteractor = null;
        this.detailsInteractor = detailsInteractor;
    }

    @Provides
    @Singleton
    @Override
    public IMainInteractor provideMainInteractor() {
        return mainInteractor;
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
