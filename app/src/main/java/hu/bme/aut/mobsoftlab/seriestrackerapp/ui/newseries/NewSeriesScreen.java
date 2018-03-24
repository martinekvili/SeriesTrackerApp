package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public interface NewSeriesScreen {

    /**
     * Called when the user wants to add a new saved series.
     * @param savedSeries The series to add.
     */
    void onAddNewSeries(SavedSeries savedSeries);
}
