package hu.bme.aut.mobsoftlab.seriestrackerapp.database.domain;

import com.orm.dsl.Table;
import com.orm.dsl.Unique;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

@Table
public class DBSavedSeries {

    @Unique
    String imdbID;
    String title;
    String posterUrl;

    int season;
    int episode;

    public DBSavedSeries() {
        // Default constructor is necessary for SugarRecord
    }

    public DBSavedSeries(SavedSeries savedSeries) {
        this.imdbID = savedSeries.getImdbID();
        this.title = savedSeries.getTitle();
        this.posterUrl = savedSeries.getPosterUrl();

        this.season = savedSeries.getSeason();
        this.episode = savedSeries.getEpisode();
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

    public int getSeason() {
        return season;
    }

    public int getEpisode() {
        return episode;
    }
}
