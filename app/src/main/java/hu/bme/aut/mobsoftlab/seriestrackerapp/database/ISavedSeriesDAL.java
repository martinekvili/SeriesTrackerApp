package hu.bme.aut.mobsoftlab.seriestrackerapp.database;

import java.util.List;
import java.util.Set;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public interface ISavedSeriesDAL {

    /**
     * Adds a new series to the database.
     * @param series The series to add.
     */
    void addSavedSeries(SavedSeries series);

    /**
     * Reads the list of the saved series from the database.
     * @return The list of the saved series.
     */
    List<SavedSeries> getSavedSeries();

    /**
     * Gets the IDs if the already added series from the database.
     * @return The IDs of the already added series.
     */
    Set<String> getAlreadyAddedSeriesIDs();

    /**
     * Updates a series' data in the database.
     * @param series The series to update.
     */
    void updateSavedSeries(SavedSeries series);
}
