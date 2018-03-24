package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event;

public class GetSeasonAndEpisodeCountEvent {

    private final int seasonCount;
    private final int episodeCount;

    public GetSeasonAndEpisodeCountEvent(int seasonCount, int episodeCount) {
        this.seasonCount = seasonCount;
        this.episodeCount = episodeCount;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }
}
