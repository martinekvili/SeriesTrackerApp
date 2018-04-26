package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event;

public class GetEpisodeCountEvent {

    private final int episodeCount;

    public GetEpisodeCountEvent(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    // region equals implementation

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetEpisodeCountEvent that = (GetEpisodeCountEvent) o;
        return episodeCount == that.episodeCount;
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(episodeCount).hashCode();
    }

    // endregion
}
