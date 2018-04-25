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
    }

    @Override
    public void addSavedSeries(SavedSeries series) {
        database.put(series.getImdbID(), series);
    }

    @Override
    public List<SavedSeries> getSavedSeries() {
        List<SavedSeries> result = new ArrayList<>(database.values());
        Collections.sort(result, (one, other) -> one.getTitle().compareTo(other.getTitle()));

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
