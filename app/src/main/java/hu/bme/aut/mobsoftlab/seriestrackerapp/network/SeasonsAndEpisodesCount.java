package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

public class SeasonsAndEpisodesCount {

    private final int totalSeasons;
    private final int episodesInSeason;

    public SeasonsAndEpisodesCount(int totalSeasons, int episodesInSeason) {
        this.totalSeasons = totalSeasons;
        this.episodesInSeason = episodesInSeason;
    }

    public int getTotalSeasons() {
        return totalSeasons;
    }

    public int getEpisodesInSeason() {
        return episodesInSeason;
    }
}
