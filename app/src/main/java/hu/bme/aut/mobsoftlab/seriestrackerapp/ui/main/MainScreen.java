package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import java.util.List;
import java.util.Set;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public interface MainScreen {

    /**
     * Lists the saved series on the UI.
     * @param savedSeries The list of the saved series.
     */
    void showSeriesList(List<SavedSeries> savedSeries);

    /**
     * Shows the details page for the specified series.
     * @param savedSeries The series to show the details of.
     */
    void showSeriesDetailsPage(SavedSeries savedSeries);

    /**
     * Shows the dialog to add a new series.
     * @param alreadyAddedSeries The IDs of the already added series.
     */
    void showAddSeriesDialog(Set<String> alreadyAddedSeries);

    /**
     * Shows the about page of the application.
     */
    void showAboutPage();
}
