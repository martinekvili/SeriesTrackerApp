package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.DetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.GetSeriesDetailsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.PresenterWithEvents;

public class DetailsPresenter extends PresenterWithEvents<DetailsScreen> {

    @Inject
    @Named("NetworkExecutor")
    Executor networkExecutor;

    @Inject
    DetailsInteractor interactor;

    private DetailsPresenterState state;

    public DetailsPresenter() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public DetailsPresenterState getState() {
        return state;
    }

    public void setState(DetailsPresenterState state) {
        this.state = state;
    }

    public void getSeriesDetails() {
        screen.showSeries(state.getSeries());

        if (state.getDetails() != null)
            screen.showSeriesDetails(state.getDetails());
        else
            networkExecutor.execute(() -> interactor.getSeriesDetails());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetSeriesDetailsEvent(final GetSeriesDetailsEvent event) {
        state.setDetails(event.getDetails());

        if (screen != null)
            screen.showSeriesDetails(event.getDetails());
    }

    public void navigateBack() {
        screen.navigateBack();
    }
}
