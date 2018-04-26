package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorTestBase;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event.NetworkErrorEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetSeasonAndEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.NewSeriesAddedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.SeasonsAndEpisodesCount;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class NewSeriesInteractorTest extends InteractorTestBase<NewSeriesInteractor> {

    @Before
    public void initMock() throws InstantiationException, IllegalAccessException {
        super.initMock(NewSeriesInteractor.class);
    }

    @Test
    public void getSearchResults_isCorrect() throws IOException {
        // Given
        List<SeriesSearchResult> searchResult = new ArrayList<>();
        searchResult.add(new SeriesSearchResult("tt2861424", "Rick and Morty", "_dummy_"));
        searchResult.add(new SeriesSearchResult("tt1486217", "Richie Rich", "_dummy_"));

        Mockito.when(apiClient.getSeriesSearchResult("ric")).thenReturn(searchResult);

        // When
        List<SeriesSearchResult> result = interactor.getSearchResults("ric");

        // Then
        Assert.assertArrayEquals(searchResult.toArray(), result.toArray());
    }

    @Test
    public void getSearchResults_networkError() throws IOException {
        // Given
        Mockito.when(apiClient.getSeriesSearchResult("ric")).thenThrow(new IOException("Some error happened"));

        // When
        List<SeriesSearchResult> result = interactor.getSearchResults("ric");

        // Then
        Mockito.verify(eventSender).send(new NetworkErrorEvent("Some error happened"));
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void getSeasonAndEpisodeCount_isCorrect() throws IOException {
        // Given
        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 1)).thenReturn(new SeasonsAndEpisodesCount(4, 10));

        // When
        interactor.getSeasonAndEpisodeCount("tt2861424");

        // Then
        Mockito.verify(eventSender).send(new GetSeasonAndEpisodeCountEvent(4, 10));
    }

    @Test
    public void getSeasonAndEpisodeCount_networkError() throws IOException {
        // Given
        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 1)).thenThrow(new IOException("An error, again"));

        // When
        interactor.getSeasonAndEpisodeCount("tt2861424");

        // Then
        Mockito.verify(eventSender).send(new NetworkErrorEvent("An error, again"));
    }

    @Test
    public void getEpisodeCount_isCorrect() throws IOException {
        // Given
        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 3)).thenReturn(new SeasonsAndEpisodesCount(4, 12));

        // When
        interactor.getEpisodeCount("tt2861424", 3);

        // Then
        Mockito.verify(eventSender).send(new GetEpisodeCountEvent(12));
    }

    @Test
    public void getEpisodeCount_networkError() throws IOException {
        // Given
        Mockito.when(apiClient.getSeasonsAndEpisodesCount("tt2861424", 3)).thenThrow(new IOException("An error, again again"));

        // When
        interactor.getEpisodeCount("tt2861424", 3);

        // Then
        Mockito.verify(eventSender).send(new NetworkErrorEvent("An error, again again"));
    }

    @Test
    public void addNewSeries_isCorrect() {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);

        // When
        interactor.addNewSeries(series);

        // Then
        Mockito.verify(dal).addSavedSeries(series);
        Mockito.verify(eventSender).send(Mockito.any(NewSeriesAddedEvent.class));
    }
}
