package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.database.ISavedSeriesDAL;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.GetSeriesDetailsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.SavedSeriesUpdatedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.StepToNextEpisodeEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class DetailsInteractor {

    @Inject
    ISavedSeriesDAL savedSeriesDAL;

    public DetailsInteractor() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public void getEpisodeDetails(SavedSeries series) {
        // TODO get the current episode (+ season to determine whether this episode is the last)
        EventBus.getDefault().post(new GetSeriesDetailsEvent(new EpisodeDetails(null, null, false)));
    }

    public void stepToNextEpisode(SavedSeries series) {
        // TODO get the current season (episodes, is there next season), and modify the current episode accordingly; get the new current episode
        EventBus.getDefault().post(new StepToNextEpisodeEvent(series, new EpisodeDetails(null, null, false)));
    }

    public void updateSavedSeries(SavedSeries series) {
        savedSeriesDAL.updateSavedSeries(series);
        EventBus.getDefault().post(new SavedSeriesUpdatedEvent());
    }
}
