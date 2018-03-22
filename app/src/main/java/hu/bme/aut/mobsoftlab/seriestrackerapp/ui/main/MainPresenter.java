package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.MainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetAlreadyAddedSeriesEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    @Named("DatabaseExecutor")
    Executor databaseExecutor;

    @Inject
    MainInteractor mainInteractor;

    public MainPresenter() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    public void attachScreen(MainScreen mainScreen) {
        super.attachScreen(mainScreen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void getSeriesList() {
        databaseExecutor.execute(() -> mainInteractor.getSeriesList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetSeriesListEvent(final GetSeriesListEvent event) {
        if (screen != null)
            screen.showSeriesList(event.getSeries());
    }

    public void selectSeries(SavedSeries series) {
        screen.showSeriesDetailsPage(series);
    }

    public void addNewSeries() {
        databaseExecutor.execute(() -> mainInteractor.getAlreadyAddedSeries());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetAlreadyAddedSeriesEvent(final GetAlreadyAddedSeriesEvent event) {
        if (screen != null)
            screen.showAddSeriesDialog(event.getAlreadyAddedSeries());
    }

    public void selectAboutPage() {
        screen.showAboutPage();
    }
}
