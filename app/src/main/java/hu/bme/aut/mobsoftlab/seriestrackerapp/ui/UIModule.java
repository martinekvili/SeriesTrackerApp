package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about.AboutPresenter;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details.DetailsPresenter;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main.MainPresenter;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries.NewSeriesPresenter;

@Module
public class UIModule {

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public AboutPresenter provideAboutPresenter() {
        return new AboutPresenter();
    }

    @Provides
    public DetailsPresenter provideDetailsPresenter() {
        return new DetailsPresenter();
    }

    @Provides
    public NewSeriesPresenter provideNewSeriesPresenter() {
        return new NewSeriesPresenter();
    }
}
