package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries;

import com.crashlytics.android.Crashlytics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.IEventSender;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event.NetworkErrorEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetSeasonAndEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.NewSeriesAddedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.IOmdbClient;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.SeasonsAndEpisodesCount;
import io.fabric.sdk.android.Fabric;

public class NewSeriesInteractor implements INewSeriesInteractor {

    @Inject
    IEventSender eventSender;

    @Inject
    IOmdbClient omdbClient;

    @Inject
    ISavedSeriesDAL savedSeriesDAL;

    public NewSeriesInteractor() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    public List<SeriesSearchResult> getSearchResults(String prefix) {
        try {
            return omdbClient.getSeriesSearchResult(prefix);
        } catch (IOException e) {
            if (Fabric.isInitialized())
                Crashlytics.logException(e);

            eventSender.send(new NetworkErrorEvent(e.getMessage()));
            return new ArrayList<>();
        }
    }

    @Override
    public void getSeasonAndEpisodeCount(String imdbID) {
        try {
            SeasonsAndEpisodesCount count = omdbClient.getSeasonsAndEpisodesCount(imdbID, 1);
            eventSender.send(new GetSeasonAndEpisodeCountEvent(count.getTotalSeasons(), count.getEpisodesInSeason()));
        } catch (IOException e) {
            if (Fabric.isInitialized())
                Crashlytics.logException(e);

            eventSender.send(new NetworkErrorEvent(e.getMessage()));
        }
    }

    @Override
    public void getEpisodeCount(String imdbID, int season) {
        try {
            SeasonsAndEpisodesCount count = omdbClient.getSeasonsAndEpisodesCount(imdbID, season);
            eventSender.send(new GetEpisodeCountEvent(count.getEpisodesInSeason()));
        } catch (IOException e) {
            if (Fabric.isInitialized())
                Crashlytics.logException(e);

            eventSender.send(new NetworkErrorEvent(e.getMessage()));
        }
    }

    @Override
    public void addNewSeries(SavedSeries series) {
        savedSeriesDAL.addSavedSeries(series);
        eventSender.send(new NewSeriesAddedEvent());
    }
}
