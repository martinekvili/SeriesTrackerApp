package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesDetails;

public interface DetailsScreen {

    /**
     * Shows the locally saved data about the series.
     * @param series The series to show.
     */
    void showSeries(SavedSeries series);

    /**
     * Shows the details gathered online.
     * @param details The details of the series to show.
     */
    void showSeriesDetails(SeriesDetails details);

    /**
     * Navigates back to the main page.
     */
    void navigateBack();
}
