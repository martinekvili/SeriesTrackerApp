package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Named;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event.NetworkErrorEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.INewSeriesInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetSeasonAndEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.NewSeriesAddedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.PresenterWithEvents;

public class NewSeriesPresenter extends PresenterWithEvents<NewSeriesScreen> {

    @Inject
    @Named("NetworkExecutor")
    Executor networkExecutor;

    @Inject
    @Named("DatabaseExecutor")
    Executor databaseExecutor;

    @Inject
    INewSeriesInteractor interactor;

    private Set<String> alreadyAddedSeries;
    private SavedSeries series;

    public NewSeriesPresenter() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public boolean isInitialized() {
        return alreadyAddedSeries != null;
    }

    public void initialize(Set<String> alreadyAddedSeries) {
        this.alreadyAddedSeries = alreadyAddedSeries;
    }

    /**
     * Gathers the series search results for a given prefix.
     * IMPORTANT: must be called from a background thread.
     */
    public List<SeriesSearchResult> getSearchResults(String prefix) {
        List<SeriesSearchResult> filteredResults = new ArrayList<>();

        for (SeriesSearchResult result : interactor.getSearchResults(prefix))
            if (!alreadyAddedSeries.contains(result.getImdbID()))
                filteredResults.add(result);

        return filteredResults;
    }

    public void chooseSeries(SeriesSearchResult searchResult) {
        series = new SavedSeries(searchResult);
        networkExecutor.execute(() -> interactor.getSeasonAndEpisodeCount(series.getImdbID()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetSeasonAndEpisodeCountEvent(final GetSeasonAndEpisodeCountEvent event) {
        if (screen != null) {
            screen.setSeasonCount(event.getSeasonCount());
            screen.setEpisodeCount(event.getEpisodeCount());
        }
    }

    public void chooseSeason(int season) {
        series.setSeason(season);
        networkExecutor.execute(() -> interactor.getEpisodeCount(series.getImdbID(), series.getSeason()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetEpisodeCountEvent(final GetEpisodeCountEvent event) {
        if (screen != null)
            screen.setEpisodeCount(event.getEpisodeCount());
    }

    public void chooseEpisode(int episode) {
        series.setEpisode(episode);
    }

    public void addNewSeries() {
        databaseExecutor.execute(() -> interactor.addNewSeries(series));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewSeriesAddedEvent(final NewSeriesAddedEvent event) {
        if (screen != null)
            screen.dismissDialog();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkErrorEvent(final NetworkErrorEvent event) {
        if (screen != null)
            screen.showNetworkErrorMessage(event.getErrorMessage());
    }
}
