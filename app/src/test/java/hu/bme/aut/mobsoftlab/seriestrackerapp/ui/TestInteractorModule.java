package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

import javax.inject.Singleton;

import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.IAboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.IDetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.IMainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.INewSeriesInteractor;

public class TestInteractorModule extends InteractorModule {

    private final IMainInteractor mainInteractor;
    private final IAboutInteractor aboutInteractor;
    private final IDetailsInteractor detailsInteractor;
    private final INewSeriesInteractor newSeriesInteractor;

    public TestInteractorModule(IMainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
        this.aboutInteractor = null;
        this.detailsInteractor = null;
        this.newSeriesInteractor = null;
    }

    public TestInteractorModule(IAboutInteractor aboutInteractor) {
        this.mainInteractor = null;
        this.aboutInteractor = aboutInteractor;
        this.detailsInteractor = null;
        this.newSeriesInteractor = null;
    }

    public TestInteractorModule(IDetailsInteractor detailsInteractor) {
        this.mainInteractor = null;
        this.aboutInteractor = null;
        this.detailsInteractor = detailsInteractor;
        this.newSeriesInteractor = null;
    }

    public TestInteractorModule(INewSeriesInteractor newSeriesInteractor) {
        this.mainInteractor = null;
        this.aboutInteractor = null;
        this.detailsInteractor = null;
        this.newSeriesInteractor = newSeriesInteractor;
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
    public INewSeriesInteractor provideNewSeriesInteractor() {
        return newSeriesInteractor;
    }
}
