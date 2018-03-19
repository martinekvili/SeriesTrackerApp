package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import java.util.HashSet;
import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public interface MainScreen {

    /**
     * Lists the saved series on the UI.
     * @param savedSeries The list of the saved series.
     */
    void showSavedSeries(List<SavedSeries> savedSeries);

    /**
     * Shows the dialog to add a new series.
     * @param alreadyAddedSeries The IDs of the already added series.
     */
    void showAddSeriesDialog(HashSet<String> alreadyAddedSeries);
}
