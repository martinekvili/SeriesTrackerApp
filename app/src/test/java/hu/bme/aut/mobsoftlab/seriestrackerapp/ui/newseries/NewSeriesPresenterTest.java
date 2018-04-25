package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.TestSeriesTrackerApplicationHelper;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.common.event.NetworkErrorEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.INewSeriesInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.GetSeasonAndEpisodeCountEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.newseries.event.NewSeriesAddedEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.TestInteractorModule;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class NewSeriesPresenterTest {

    private INewSeriesInteractor interactor;
    private NewSeriesScreen screen;
    private NewSeriesPresenter presenter;

    @Before
    public void initMock() {
        interactor = Mockito.mock(INewSeriesInteractor.class);
        TestSeriesTrackerApplicationHelper.createTestInjector(new TestInteractorModule(interactor));

        screen = Mockito.mock(NewSeriesScreen.class);

        presenter = new NewSeriesPresenter();
        presenter.attachScreen(screen);
    }

    @Test
    public void getSearchResults_nothingToFilter() {
        // Given
        Set<String> filter = new HashSet<>();
        presenter.initialize(filter);
        Assert.assertTrue(presenter.isInitialized());

        List<SeriesSearchResult> actualResult = new ArrayList<>();
        actualResult.add(new SeriesSearchResult("tt2861424", "Rick and Morty", "_dummy_"));
        actualResult.add(new SeriesSearchResult("tt1486217", "Richie Rich", "_dummy_"));
        Mockito.when(interactor.getSearchResults("arc")).thenReturn(actualResult);

        // When
        List<SeriesSearchResult> result = presenter.getSearchResults("arc");

        // Then
        Assert.assertArrayEquals(actualResult.toArray(), result.toArray());
    }

    @Test
    public void getSearchResults_filtered() {
        // Given
        Set<String> filter = new HashSet<>();
        filter.add("tt2861424");
        filter.add("tt1486217");
        presenter.initialize(filter);

        List<SeriesSearchResult> actualResult = new ArrayList<>();
        actualResult.add(new SeriesSearchResult("tt2861424", "Rick and Morty", "_dummy_"));
        actualResult.add(new SeriesSearchResult("tt1486217", "Richie Rich", "_dummy_"));
        Mockito.when(interactor.getSearchResults("arc")).thenReturn(actualResult);

        // When
        List<SeriesSearchResult> result = presenter.getSearchResults("arc");

        // Then
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void chooseSeries_isCorrect() {
        // Given
        SeriesSearchResult searchResult = new SeriesSearchResult("tt2861424", "Rick and Morty", "_dummy_");

        // When
        presenter.chooseSeries(searchResult);
        Mockito.verify(interactor).getSeasonAndEpisodeCount("tt2861424");

        presenter.onGetSeasonAndEpisodeCountEvent(new GetSeasonAndEpisodeCountEvent(4, 10));

        // Then
        Mockito.verify(screen).setSeasonCount(4);
        Mockito.verify(screen).setEpisodeCount(10);
    }

    @Test
    public void chooseSeries_networkError() {
        // Given
        SeriesSearchResult searchResult = new SeriesSearchResult("tt2861424", "Rick and Morty", "_dummy_");

        // When
        presenter.chooseSeries(searchResult);
        Mockito.verify(interactor).getSeasonAndEpisodeCount("tt2861424");

        presenter.onNetworkErrorEvent(new NetworkErrorEvent("Some error happened"));

        // Then
        Mockito.verify(screen).showNetworkErrorMessage("Some error happened");
    }

    @Test
    public void addNewSeries_isCorrect() {
        // Given
        SeriesSearchResult searchResult = new SeriesSearchResult("tt2861424", "Rick and Morty", "_dummy_");
        SavedSeries series = new SavedSeries(searchResult);
        series.setSeason(3);
        series.setEpisode(9);

        // When
        presenter.chooseSeries(searchResult);
        Mockito.verify(interactor).getSeasonAndEpisodeCount("tt2861424");

        presenter.onGetSeasonAndEpisodeCountEvent(new GetSeasonAndEpisodeCountEvent(4, 10));
        Mockito.verify(screen).setSeasonCount(4);
        Mockito.verify(screen).setEpisodeCount(10);

        presenter.chooseSeason(3);
        Mockito.verify(interactor).getEpisodeCount("tt2861424", 3);

        presenter.onGetEpisodeCountEvent(new GetEpisodeCountEvent(11));
        Mockito.verify(screen).setEpisodeCount(11);

        presenter.chooseEpisode(9);
        presenter.addNewSeries();
        Mockito.verify(interactor).addNewSeries(series);

        presenter.onNewSeriesAddedEvent(new NewSeriesAddedEvent());

        // Then
        Mockito.verify(screen).dismissDialog();
    }
}
