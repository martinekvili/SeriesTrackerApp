package hu.bme.aut.mobsoftlab.seriestrackerapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.DatabaseModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.UIModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about.AboutActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about.AboutPresenter;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main.MainActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main.MainPresenter;

@Singleton
@Component(modules = {UIModule.class, InteractorModule.class, DatabaseModule.class})
public interface SeriesTrackerApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(MainPresenter mainPresenter);

    void inject(AboutActivity aboutActivity);

    void inject(AboutPresenter aboutPresenter);
}
