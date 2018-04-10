package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

public interface NewSeriesScreen {

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
     * Dismisses the dialog.
     */
    void dismissDialog();
}
