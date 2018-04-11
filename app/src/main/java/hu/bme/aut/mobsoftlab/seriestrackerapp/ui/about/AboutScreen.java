package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about;

public interface AboutScreen {

    /**
     * Shows the application version on the UI.
     *
     * @param versionName The application version.
     */
    void showVersionName(String versionName);

    /**
     * Navigates back to the main screen.
     */
    void navigateBack();
}
