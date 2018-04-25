package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SeriesData;

public class SeriesDataWithEpisodeCount {

    private final SeriesData seriesData;
    private final int episodeCount;

    public SeriesDataWithEpisodeCount(String imdbID, String title, String posterUrl, String plot, String imdbRating, int totalSeasons, int episodeCount) {
        this(new SeriesData()
                .imdbID(imdbID)
                .title(title)
                .poster(posterUrl)
                .plot(plot)
                .imdbRating(imdbRating)
                .totalSeasons(totalSeasons),
                episodeCount);
    }

    public SeriesDataWithEpisodeCount(SeriesData seriesData, int episodeCount) {
        this.seriesData = seriesData;
        this.episodeCount = episodeCount;
    }

    public SeriesData getSeriesData() {
        return seriesData;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }
}
