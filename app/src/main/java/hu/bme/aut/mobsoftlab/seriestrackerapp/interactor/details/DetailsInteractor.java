package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details;

import java.io.IOException;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.IEventSender;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event.NetworkErrorEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.GetSeriesDetailsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.SavedSeriesUpdatedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.StepToNextEpisodeEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.IOmdbClient;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.SeasonsAndEpisodesCount;

public class DetailsInteractor {

    @Inject
    IEventSender eventSender;

    @Inject
    IOmdbClient omdbClient;

    @Inject
    ISavedSeriesDAL savedSeriesDAL;

    public DetailsInteractor() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public void getEpisodeDetails(SavedSeries series) {
        try {
            EpisodeDetails episodeDetails = omdbClient.getEpisodeDetails(series.getImdbID(), series.getSeason(), series.getEpisode());

            SeasonsAndEpisodesCount count = omdbClient.getSeasonsAndEpisodesCount(series.getImdbID(), series.getSeason());
            episodeDetails.setLastEpisode(isLastEpisode(series, count));

            eventSender.send(new GetSeriesDetailsEvent(episodeDetails));
        } catch (IOException e) {
            eventSender.send(new NetworkErrorEvent(e.getMessage()));
        }
    }

    public void stepToNextEpisode(SavedSeries series) {
        try {
            SeasonsAndEpisodesCount count = omdbClient.getSeasonsAndEpisodesCount(series.getImdbID(), series.getSeason());

            boolean isLastEpisode;
            if (series.getEpisode() < count.getEpisodesInSeason()) {    // there are still more episodes in the season
                series.setEpisode(series.getEpisode() + 1);
                isLastEpisode = isLastEpisode(series, count);
            } else if (series.getSeason() < count.getTotalSeasons()) {  // there are more seasons
                series.setSeason(series.getSeason() + 1);
                series.setEpisode(1);

                // Assume that there are still more episodes, since not many series have only one episode in a season.
                // This is an intentional optimization here to save us a third round-trip to the API.
                isLastEpisode = false;
            } else {                                                    // this is the last episode
                isLastEpisode = true;
            }

            EpisodeDetails episodeDetails = omdbClient.getEpisodeDetails(series.getImdbID(), series.getSeason(), series.getEpisode());
            episodeDetails.setLastEpisode(isLastEpisode);

            eventSender.send(new StepToNextEpisodeEvent(series, episodeDetails));
        } catch (IOException e) {
            eventSender.send(new NetworkErrorEvent(e.getMessage()));
        }
    }

    private boolean isLastEpisode(SavedSeries series, SeasonsAndEpisodesCount count) {
        return series.getSeason() == count.getTotalSeasons() && series.getEpisode() == count.getEpisodesInSeason();
    }

    public void updateSavedSeries(SavedSeries series) {
        savedSeriesDAL.updateSavedSeries(series);
        eventSender.send(new SavedSeriesUpdatedEvent());
    }
}
