package hu.bme.aut.mobsoftlab.seriestrackerapp.model;

public class SeriesSearchResult {

    private final String imdbID;
    private final String title;
    private final String posterUrl;

    public SeriesSearchResult(String imdbID, String title, String posterUrl) {
        this.imdbID = imdbID;
        this.title = title;
        this.posterUrl = posterUrl;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
