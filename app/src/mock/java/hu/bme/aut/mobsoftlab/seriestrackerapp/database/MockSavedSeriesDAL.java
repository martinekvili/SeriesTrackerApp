package hu.bme.aut.mobsoftlab.seriestrackerapp.database;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class MockSavedSeriesDAL implements ISavedSeriesDAL {

    private final Map<String, SavedSeries> database;

    public MockSavedSeriesDAL() {
        this.database = new HashMap<>();

        database.put("tt0063889", new SavedSeries("tt0063889", "Dastardly and Muttley in Their Flying Machines", "https://images-na.ssl-images-amazon.com/images/M/MV5BMzhhNzY1MzUtYjE1OC00ZDVkLWE4Y2YtZmEzYWZiNTdiOGE1XkEyXkFqcGdeQXVyNjExODE1MDc@._V1_SX300.jpg", 1, 13));
        database.put("tt2861424", new SavedSeries("tt2861425", "Rick and Morty", "https://ia.media-imdb.com/images/M/MV5BMjRiNDRhNGUtMzRkZi00MThlLTg0ZDMtNjc5YzFjYmFjMmM4XkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_SX300.jpg", 3, 9));
        database.put("tt0903747", new SavedSeries("tt0903747", "Breaking Bad", "https://ia.media-imdb.com/images/M/MV5BZDNhNzhkNDctOTlmOS00NWNmLWEyODQtNWMxM2UzYmJiNGMyXkEyXkFqcGdeQXVyNTMxMjgxMzA@._V1_SX300.jpg", 5, 2));
        database.put("tt1486217", new SavedSeries("tt1486217", "Archer", "https://ia.media-imdb.com/images/M/MV5BMTg3NTMwMzY2OF5BMl5BanBnXkFtZTgwMDcxMjQ0NDE@._V1_SX300.jpg", 2, 12));
        database.put("tt1486218", new SavedSeries("tt1486217", "Archer", "https://ia.media-imdb.com/images/M/MV5BMTg3NTMwMzY2OF5BMl5BanBnXkFtZTgwMDcxMjQ0NDE@._V1_SX300.jpg", 2, 12));
        database.put("tt1486219", new SavedSeries("tt1486217", "Archer", "https://ia.media-imdb.com/images/M/MV5BMTg3NTMwMzY2OF5BMl5BanBnXkFtZTgwMDcxMjQ0NDE@._V1_SX300.jpg", 2, 12));
        database.put("tt1486220", new SavedSeries("tt1486217", "Archer", "https://ia.media-imdb.com/images/M/MV5BMTg3NTMwMzY2OF5BMl5BanBnXkFtZTgwMDcxMjQ0NDE@._V1_SX300.jpg", 2, 12));
        database.put("tt1486221", new SavedSeries("tt1486217", "Archer", "https://ia.media-imdb.com/images/M/MV5BMTg3NTMwMzY2OF5BMl5BanBnXkFtZTgwMDcxMjQ0NDE@._V1_SX300.jpg", 2, 12));
        database.put("tt1486222", new SavedSeries("tt1486217", "Archer", "https://ia.media-imdb.com/images/M/MV5BMTg3NTMwMzY2OF5BMl5BanBnXkFtZTgwMDcxMjQ0NDE@._V1_SX300.jpg", 2, 12));
    }

    @Override
    public void addSavedSeries(SavedSeries series) {
        database.put(series.getImdbID(), series);
    }

    @Override
    public List<SavedSeries> getSavedSeries() {
        List<SavedSeries> result = new ArrayList<>(database.values());
        Collections.sort(result, (one, other) -> one.getTitle().compareTo(other.getTitle()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Set<String> getAlreadyAddedSeriesIDs() {
        return database.keySet();
    }

    @Override
    public void updateSavedSeries(SavedSeries series) {
        database.put(series.getImdbID(), series);
    }
}
