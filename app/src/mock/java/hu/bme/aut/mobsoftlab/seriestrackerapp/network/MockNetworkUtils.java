package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.ModelApiResponse;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SeriesData;

public class MockNetworkUtils {

    public static ModelApiResponse createModelApiResponse(SeriesData seriesData) {
        return new ModelApiResponse()
                .response(true)
                .title(seriesData.getTitle())
                .year(seriesData.getYear())
                .rated(seriesData.getRated())
                .released(seriesData.getReleased())
                .season(seriesData.getSeason())
                .episode(seriesData.getEpisode())
                .runtime(seriesData.getRuntime())
                .genre(seriesData.getGenre())
                .director(seriesData.getDirector())
                .writer(seriesData.getWriter())
                .actors(seriesData.getActors())
                .plot(seriesData.getPlot())
                .language(seriesData.getLanguage())
                .country(seriesData.getCountry())
                .awards(seriesData.getAwards())
                .poster(seriesData.getPoster())
                .ratings(seriesData.getRatings())
                .metascore(seriesData.getMetascore())
                .imdbRating(seriesData.getImdbRating())
                .imdbVotes(seriesData.getImdbVotes())
                .imdbID(seriesData.getImdbID())
                .seriesID(seriesData.getSeriesID())
                .type(seriesData.getType())
                .totalSeasons(seriesData.getTotalSeasons());
    }
}
