package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about.AboutPresenter;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main.MainPresenter;

@Module
public class UIModule {

    @Provides
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    @Singleton
    public AboutPresenter provideAboutPresenter() {
        return new AboutPresenter();
    }
}
