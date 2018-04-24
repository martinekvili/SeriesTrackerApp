package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event.NetworkErrorEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetSeasonAndEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.NewSeriesAddedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.IOmdbClient;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.SeasonsAndEpisodesCount;

public class NewSeriesInteractor {

    @Inject
    IOmdbClient omdbClient;
    
    @Inject
    ISavedSeriesDAL savedSeriesDAL;

    public NewSeriesInteractor() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public List<SeriesSearchResult> getSearchResults(String prefix) {
        try {
            return omdbClient.getSeriesSearchResult(prefix);
        } catch (IOException e) {
            EventBus.getDefault().post(new NetworkErrorEvent(e.getMessage()));
            return new ArrayList<>();
        }
    }

    public void getSeasonAndEpisodeCount(String imdbID) {
        try {
            SeasonsAndEpisodesCount count = omdbClient.getSeasonsAndEpisodesCount(imdbID, 1);
            EventBus.getDefault().post(new GetSeasonAndEpisodeCountEvent(count.getTotalSeasons(), count.getEpisodesInSeason()));
        } catch (IOException e) {
            EventBus.getDefault().post(new NetworkErrorEvent(e.getMessage()));
        }
    }

    public void getEpisodeCount(String imdbID, int season) {
        try {
            SeasonsAndEpisodesCount count = omdbClient.getSeasonsAndEpisodesCount(imdbID, season);
            EventBus.getDefault().post(new GetEpisodeCountEvent(count.getEpisodesInSeason()));
        } catch (IOException e) {
            EventBus.getDefault().post(new NetworkErrorEvent(e.getMessage()));
        }
    }
    
    public void addNewSeries(SavedSeries series) {
        savedSeriesDAL.addSavedSeries(series);
        EventBus.getDefault().post(new NewSeriesAddedEvent());
    }
}
