package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public interface NewSeriesScreen {

    /**
     * Called when the user wants to add a new saved series.
     * @param savedSeries The series to add.
     */
    void onAddNewSeries(SavedSeries savedSeries);

    /**
     * Sets the max season number on the UI.
     * @param seasonCount The max season count.
     */
    void setSeasonCount(int seasonCount);

    /**
     * Sets the max episode count on the UI.
     * @param episodeCount The max episode count.
     */
    void setEpisodeCount(int episodeCount);

    /**
     * Shows a network error message.
     * @param errorMessage The error message to show.
     */
    void showNetworkErrorMessage(String errorMessage);
}
