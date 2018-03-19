package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    private static MainPresenter instance = null;

    private MainPresenter() {
    }

    public static MainPresenter getInstance() {
        if (instance == null) {
            instance = new MainPresenter();
        }
        return instance;
    }

    public void showSavedSeries() {
        // TODO
    }

    public void showNewSeriesDialog() {
        // TODO
    }
}
