package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.DetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.GetSeriesDetailsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.StepToNextEpisodeEvent;
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
            screen.showEpisodeDetails(state.getDetails());
        else
            networkExecutor.execute(() -> interactor.getEpisodeDetails(state.getSeries()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetSeriesDetailsEvent(final GetSeriesDetailsEvent event) {
        state.setDetails(event.getDetails());

        if (screen != null)
            screen.showEpisodeDetails(state.getDetails());
    }

    public void stepToNextEpisode() {
        // copy the series object, because the other thread is going to modify it - do not want any race conditions to happen
        networkExecutor.execute(() -> interactor.stepToNextEpisode(state.getSeries().copy()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStepToNextEpisodeEvent(final StepToNextEpisodeEvent event) {
        state.setSeries(event.getSeries());
        state.setDetails(event.getDetails());

        if (screen != null) {
            screen.showSeries(state.getSeries());
            screen.showEpisodeDetails(state.getDetails());
        }
    }

    public void navigateBack() {
        screen.navigateBack();
    }
}
