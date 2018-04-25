package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.TestSeriesTrackerApplicationHelper;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event.NetworkErrorEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.IDetailsInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.GetSeriesDetailsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.SavedSeriesUpdatedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.StepToNextEpisodeEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.TestInteractorModule;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class DetailsPresenterTest {

    private IDetailsInteractor interactor;
    private DetailsScreen screen;
    private DetailsPresenter presenter;

    @Before
    public void initMock() {
        interactor = Mockito.mock(IDetailsInteractor.class);
        TestSeriesTrackerApplicationHelper.createTestInjector(new TestInteractorModule(interactor));

        screen = Mockito.mock(DetailsScreen.class);

        presenter = new DetailsPresenter();
        presenter.attachScreen(screen);
    }

    @Test
    public void getSeriesDetails_isCached() {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);
        EpisodeDetails details = new EpisodeDetails("_dummy_", "8.3");

        DetailsPresenterState state = new DetailsPresenterState(series);
        state.setDetails(details);
        presenter.setState(state);

        // When
        presenter.getSeriesDetails();

        // Then
        Mockito.verify(screen).showSeries(series);
        Mockito.verify(screen).showEpisodeDetails(details);
    }

    @Test
    public void getSeriesDetails_notCached() {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);
        EpisodeDetails details = new EpisodeDetails("_dummy_", "8.3");

        DetailsPresenterState state = new DetailsPresenterState(series);
        presenter.setState(state);

        // When
        presenter.getSeriesDetails();
        Mockito.verify(interactor).getEpisodeDetails(series);
        presenter.onGetSeriesDetailsEvent(new GetSeriesDetailsEvent(details));

        // Then
        Mockito.verify(screen).showSeries(series);
        Mockito.verify(screen).showEpisodeDetails(details);
    }

    @Test
    public void stepToNextEpisode_isCorrect() {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);

        SavedSeries nextEpisode = series.copy();
        nextEpisode.setSeason(4);
        nextEpisode.setSeason(1);

        EpisodeDetails details = new EpisodeDetails("_dummy_", "8.3");

        DetailsPresenterState state = new DetailsPresenterState(series);
        presenter.setState(state);

        // When
        presenter.stepToNextEpisode();
        Mockito.verify(interactor).stepToNextEpisode(series);

        presenter.onStepToNextEpisodeEvent(new StepToNextEpisodeEvent(nextEpisode, details));
        Mockito.verify(interactor).updateSavedSeries(nextEpisode);

        presenter.onSavedSeriesUpdatedEvent(new SavedSeriesUpdatedEvent());

        // Then
        Mockito.verify(screen).showSeries(nextEpisode);
        Mockito.verify(screen).showEpisodeDetails(details);
    }

    @Test
    public void stepToNextEpisode_networkError() {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);

        DetailsPresenterState state = new DetailsPresenterState(series);
        presenter.setState(state);

        // When
        presenter.stepToNextEpisode();
        Mockito.verify(interactor).stepToNextEpisode(series);

        presenter.onNetworkErrorEvent(new NetworkErrorEvent("Some network error"));

        // Then
        Mockito.verify(screen).showNetworkErrorMessage("Some network error");
    }
}
