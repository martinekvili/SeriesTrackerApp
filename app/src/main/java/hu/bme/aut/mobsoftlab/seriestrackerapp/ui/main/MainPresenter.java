package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.MainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetAlreadyAddedSeriesIDsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.PresenterWithEvents;

public class MainPresenter extends PresenterWithEvents<MainScreen> {

    @Inject
    @Named("DatabaseExecutor")
    Executor databaseExecutor;

    @Inject
    MainInteractor interactor;

    public MainPresenter() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public void getSeriesList() {
        databaseExecutor.execute(() -> interactor.getSeriesList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetSeriesListEvent(final GetSeriesListEvent event) {
        if (screen != null)
            screen.showSeriesList(event.getSeries());
    }

    public void selectSeries(SavedSeries series) {
        screen.showSeriesDetailsPage(series);
    }

    public void addNewSeriesDialog() {
        databaseExecutor.execute(() -> interactor.getAlreadyAddedSeriesIDs());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetAlreadyAddedSeriesIDsEvent(final GetAlreadyAddedSeriesIDsEvent event) {
        if (screen != null)
            screen.showAddSeriesDialog(event.getAlreadyAddedSeriesIDs());
    }

    public void selectAboutPage() {
        screen.showAboutPage();
    }
}
