package hu.bme.aut.mobsoftlab.seriestrackerapp.database;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hu.bme.aut.mobsoftlab.seriestrackerapp.database.domain.DBSavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class SavedSeriesDAL implements ISavedSeriesDAL {

    /**
     * Caches the saved series so they are not read from the database every time.
     */
    private Map<String, SavedSeries> savedSeriesCache;

    @Override
    public void addSavedSeries(SavedSeries series) {
        SugarRecord.save(new DBSavedSeries(series));

        if (savedSeriesCache != null)
            savedSeriesCache.put(series.getImdbID(), series);
    }

    @Override
    public List<SavedSeries> getSavedSeries() {
        if (savedSeriesCache != null)
            initSavedSeriesCache();

        return new ArrayList<>(savedSeriesCache.values());
    }

    @Override
    public Set<String> getAlreadyAddedSeriesIDs() {
        if (savedSeriesCache != null)
            initSavedSeriesCache();

        return savedSeriesCache.keySet();
    }

    private void initSavedSeriesCache() {
        savedSeriesCache = new HashMap<>();
        for (DBSavedSeries savedSeries : SugarRecord.listAll(DBSavedSeries.class))
            savedSeriesCache.put(
                    savedSeries.getImdbID(),
                    new SavedSeries(savedSeries.getImdbID(), savedSeries.getTitle(), savedSeries.getPosterUrl(), savedSeries.getSeason(), savedSeries.getEpisode())
            );
    }

    @Override
    public void updateSavedSeries(SavedSeries series) {
        SugarRecord.update(new DBSavedSeries(series));

        if (savedSeriesCache != null)
            savedSeriesCache.put(series.getImdbID(), series);
    }


}
