package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.MainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.PresenterWithEvents;

public class MainPresenter extends PresenterWithEvents<MainScreen> {

    @Inject
    @Named("DatabaseExecutor")
    Executor databaseExecutor;

    @Inject
    MainInteractor interactor;

    /**
     * Caches the saves series so they are not read from the database every time.
     */
    private List<SavedSeries> savedSeries;

    public MainPresenter() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public void getSeriesList() {
        if (savedSeries != null)
            screen.showSeriesList(savedSeries);
        else
            databaseExecutor.execute(() -> interactor.getSeriesList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetSeriesListEvent(final GetSeriesListEvent event) {
        savedSeries = event.getSeries();

        if (screen != null)
            screen.showSeriesList(savedSeries);
    }

    public void selectSeries(SavedSeries series) {
        screen.showSeriesDetailsPage(series);
    }

    public void addNewSeriesDialog() {
        Set<String> alreadyAddedSeries = new HashSet<>();
        for (SavedSeries series : savedSeries)
            alreadyAddedSeries.add(series.getImdbID());

        screen.showAddSeriesDialog(alreadyAddedSeries);
    }

    public void addNewSeries(SavedSeries series) {
        savedSeries.add(series);
        screen.showSeriesList(savedSeries);

        databaseExecutor.execute(() -> interactor.addNewSeries(series));
    }

    public void selectAboutPage() {
        screen.showAboutPage();
    }
}
