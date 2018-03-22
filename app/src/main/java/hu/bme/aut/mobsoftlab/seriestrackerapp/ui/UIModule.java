package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main.MainPresenter;

@Module
public class UIModule {

    @Provides
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }
}
