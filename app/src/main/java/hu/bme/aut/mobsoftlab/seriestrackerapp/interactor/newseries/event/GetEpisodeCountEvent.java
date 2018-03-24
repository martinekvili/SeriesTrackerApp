package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event;

public class GetEpisodeCountEvent {

    private final int episodeCount;

    public GetEpisodeCountEvent(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }
}
