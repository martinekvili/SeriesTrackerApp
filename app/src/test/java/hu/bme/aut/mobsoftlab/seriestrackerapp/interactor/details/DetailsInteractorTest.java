package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorTestBase;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event.NetworkErrorEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.GetSeriesDetailsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.SavedSeriesUpdatedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.details.event.StepToNextEpisodeEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.SeasonsAndEpisodesCount;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class DetailsInteractorTest extends InteractorTestBase<DetailsInteractor> {

    @Before
    public void initMock() throws InstantiationException, IllegalAccessException {
        super.initMock(DetailsInteractor.class);
    }

    @Test
    public void getEpisodeDetails_isCorrect() throws IOException {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);
        EpisodeDetails details = new EpisodeDetails("_dummy_", "8.3");
        SeasonsAndEpisodesCount count = new SeasonsAndEpisodesCount(3, 9);

        Mockito.when(apiClient.getEpisodeDetails("tt2861424", 3, 9)).thenReturn(details);
        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 3)).thenReturn(count);

        // When
        interactor.getEpisodeDetails(series);

        // Then
        Mockito.verify(eventSender).send(new GetSeriesDetailsEvent(details));
        Assert.assertTrue(details.isLastEpisode());
    }

    @Test
    public void getEpisodeDetails_networkError() throws IOException {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);
        Mockito.when(apiClient.getEpisodeDetails("tt2861424", 3, 9)).thenThrow(new IOException("Some exception"));

        // When
        interactor.getEpisodeDetails(series);

        // Then
        Mockito.verify(eventSender).send(new NetworkErrorEvent("Some exception"));
    }

    @Test
    public void getEpisodeDetails_sameSeason() throws IOException {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);
        EpisodeDetails details = new EpisodeDetails("_dummy_", "8.3");
        SeasonsAndEpisodesCount count = new SeasonsAndEpisodesCount(4, 12);

        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 3)).thenReturn(count);
        Mockito.when(apiClient.getEpisodeDetails("tt2861424", 3, 10)).thenReturn(details);

        // When
        interactor.stepToNextEpisode(series);

        // Then
        Mockito.verify(eventSender).send(new StepToNextEpisodeEvent(series, details));
        Assert.assertEquals(3, series.getSeason());
        Assert.assertEquals(10, series.getEpisode());
        Assert.assertFalse(details.isLastEpisode());
    }

    @Test
    public void getEpisodeDetails_nextSeason() throws IOException {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);
        EpisodeDetails details = new EpisodeDetails("_dummy_", "8.3");
        SeasonsAndEpisodesCount count = new SeasonsAndEpisodesCount(4, 9);

        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 3)).thenReturn(count);
        Mockito.when(apiClient.getEpisodeDetails("tt2861424", 4, 1)).thenReturn(details);

        // When
        interactor.stepToNextEpisode(series);

        // Then
        Mockito.verify(eventSender).send(new StepToNextEpisodeEvent(series, details));
        Assert.assertEquals(4, series.getSeason());
        Assert.assertEquals(1, series.getEpisode());
        Assert.assertFalse(details.isLastEpisode());
    }

    @Test
    public void getEpisodeDetails_lastEpisode() throws IOException {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);
        EpisodeDetails details = new EpisodeDetails("_dummy_", "8.3");
        SeasonsAndEpisodesCount count = new SeasonsAndEpisodesCount(3, 9);

        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 3)).thenReturn(count);
        Mockito.when(apiClient.getEpisodeDetails("tt2861424", 3, 9)).thenReturn(details);

        // When
        interactor.stepToNextEpisode(series);

        // Then
        Mockito.verify(eventSender).send(new StepToNextEpisodeEvent(series, details));
        Assert.assertEquals(3, series.getSeason());
        Assert.assertEquals(9, series.getEpisode());
        Assert.assertTrue(details.isLastEpisode());
    }

    @Test
    public void stepToNextEpisode_networkError() throws IOException {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);
        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 3)).thenThrow(new IOException("Some other exception"));

        // When
        interactor.stepToNextEpisode(series);

        // Then
        Mockito.verify(eventSender).send(new NetworkErrorEvent("Some other exception"));
    }

    @Test
    public void updateSavedSeries_isCorrect() {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);

        // When
        interactor.updateSavedSeries(series);

        // Then
        Mockito.verify(dal).updateSavedSeries(series);
        Mockito.verify(eventSender).send(Mockito.any(SavedSeriesUpdatedEvent.class));
    }
}
