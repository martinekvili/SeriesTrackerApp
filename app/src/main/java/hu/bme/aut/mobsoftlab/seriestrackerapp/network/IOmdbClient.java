package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import java.io.IOException;
import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;

public interface IOmdbClient {

    /**
     * Gets the details of an episode.
     * @param imdbID The IMDb ID of the series.
     * @param season The season number of the episode.
     * @param episode The episode number of the episode.
     * @return The details of the episode.
     * @throws IOException Throws if any error happens during accessing the API.
     */
    EpisodeDetails getEpisodeDetails(String imdbID, int season, int episode) throws IOException;

    /**
     * Gets the total number of seasons and the number of episodes in the given season in a series.
     * @param imdbID The IMDb ID of the series.
     * @param season The season number to query.
     * @return The total number of seasons and the number of episodes in the given season.
     * @throws IOException Throws if any error happens during accessing the API.
     */
    SeasonsAndEpisodesCount getSeasonsAndEpisodesCount(String imdbID, int season) throws IOException;

    /**
     * Searches for series using the given prefix.
     * @param prefix The prefix to search for.
     * @return The series with the given prefix.
     * @throws IOException Throws if any error happens during accessing the API.
     */
    List<SeriesSearchResult> getSeriesSearchResult(String prefix) throws IOException;
}
