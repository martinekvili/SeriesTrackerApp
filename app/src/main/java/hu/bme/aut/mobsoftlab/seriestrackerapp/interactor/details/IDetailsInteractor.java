package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public interface IDetailsInteractor {
    void getEpisodeDetails(SavedSeries series);

    void stepToNextEpisode(SavedSeries series);

    void updateSavedSeries(SavedSeries series);
}
