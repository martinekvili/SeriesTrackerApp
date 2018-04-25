package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries;

import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;

public interface INewSeriesInteractor {
    List<SeriesSearchResult> getSearchResults(String prefix);

    void getSeasonAndEpisodeCount(String imdbID);

    void getEpisodeCount(String imdbID, int season);

    void addNewSeries(SavedSeries series);
}
