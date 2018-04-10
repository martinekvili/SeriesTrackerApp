package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public interface DetailsScreen {

    /**
     * Shows the locally saved data about the series.
     *
     * @param series The series to show.
     */
    void showSeries(SavedSeries series);

    /**
     * Shows the details of the episode gathered online.
     *
     * @param details The details of the episode to show.
     */
    void showEpisodeDetails(EpisodeDetails details);

    /**
     * Navigates back to the main page.
     */
    void navigateBack();
}
