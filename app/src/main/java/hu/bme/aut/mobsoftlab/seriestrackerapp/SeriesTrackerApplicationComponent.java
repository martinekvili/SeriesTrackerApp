package hu.bme.aut.mobsoftlab.seriestrackerapp;

import javax.inject.Singleton;

import dagger.Component;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.DatabaseModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.DetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.NewSeriesInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.NetworkModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.OmdbClient;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.UIModule;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about.AboutActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about.AboutPresenter;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details.DetailsActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details.DetailsPresenter;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main.MainActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main.MainPresenter;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries.NewSeriesDialog;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries.NewSeriesPresenter;

@Singleton
@Component(modules = { UIModule.class, InteractorModule.class, DatabaseModule.class, NetworkModule.class })
public interface SeriesTrackerApplicationComponent {

    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);

    void inject(AboutActivity aboutActivity);
    void inject(AboutPresenter aboutPresenter);
    
    void inject(DetailsActivity detailsActivity);
    void inject(DetailsPresenter detailsPresenter);
    void inject(DetailsInteractor detailsInteractor);

    void inject(NewSeriesDialog newSeriesDialog);
    void inject(NewSeriesPresenter newSeriesPresenter);
    void inject(NewSeriesInteractor newSeriesInteractor);

    void inject(OmdbClient omdbClient);
}
