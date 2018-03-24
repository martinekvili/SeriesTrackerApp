package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetSeasonAndEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;

public class NewSeriesInteractor {

    public List<SeriesSearchResult> getSearchResults(String prefix) {
        // TODO get series search results from API
        return new ArrayList<>();
    }

    public void getSeasonAndEpisodeCount(String imdbID) {
        // TODO get the details for the first season of the series (gives total season count and number of episodes in first season)
        EventBus.getDefault().post(new GetSeasonAndEpisodeCountEvent(0, 0));
    }

    public void getEpisodeCount(String imdbID, int season) {
        // TODO get the details for the given season of the series for the number of episodes
        EventBus.getDefault().post(new GetEpisodeCountEvent(0));
    }
}
