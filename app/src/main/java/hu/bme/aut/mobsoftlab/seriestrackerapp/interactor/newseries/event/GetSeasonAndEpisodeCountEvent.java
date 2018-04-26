package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event;

import hu.bme.aut.mobsoftlab.seriestrackerapp.util.ObjectsHelper;

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

    // region equals implementation

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetSeasonAndEpisodeCountEvent that = (GetSeasonAndEpisodeCountEvent) o;
        return seasonCount == that.seasonCount &&
                episodeCount == that.episodeCount;
    }

    @Override
    public int hashCode() {
        return ObjectsHelper.hash(seasonCount, episodeCount);
    }


    // endregion
}
