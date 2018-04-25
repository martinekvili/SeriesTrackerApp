package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

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
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.IMainInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetAlreadyAddedSeriesIDsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.TestInteractorModule;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class MainPresenterTest {

    private IMainInteractor interactor;
    private MainScreen screen;
    private MainPresenter presenter;

    @Before
    public void initMock() {
        interactor = Mockito.mock(IMainInteractor.class);
        TestSeriesTrackerApplicationHelper.createTestInjector(new TestInteractorModule(interactor));

        screen = Mockito.mock(MainScreen.class);

        presenter = new MainPresenter();
        presenter.attachScreen(screen);
    }

    @Test
    public void getSeriesList_isCorrect() {
        // Given
        List<SavedSeries> seriesList = new ArrayList<>();
        seriesList.add(new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9));
        seriesList.add(new SavedSeries("tt0903747", "Breaking Bad", "_dummy_", 5, 12));

        // When
        presenter.getSeriesList();
        Mockito.verify(interactor).getSeriesList();

        presenter.onGetSeriesListEvent(new GetSeriesListEvent(seriesList));

        // Then
        Mockito.verify(screen).showSeriesList(seriesList);
    }

    @Test
    public void selectSeries_isCorrect() {
        // Given
        SavedSeries series = new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9);

        // When
        presenter.selectSeries(series);

        // Then
        Mockito.verify(screen).showSeriesDetailsPage(series);
    }

    @Test
    public void addNewSeriesDialog_isCorrect() {
        // Given
        Set<String> ids = new HashSet<>();
        ids.add("tt2861424");
        ids.add("tt0903747");

        // When
        presenter.addNewSeriesDialog();
        Mockito.verify(interactor).getAlreadyAddedSeriesIDs();

        presenter.onGetAlreadyAddedSeriesIDsEvent(new GetAlreadyAddedSeriesIDsEvent(ids));

        // Then
        Mockito.verify(screen).showAddSeriesDialog(ids);
    }

    @Test
    public void selectAboutPage_isCorrect() {
        // When
        presenter.selectAboutPage();

        // Then
        Mockito.verify(screen).showAboutPage();
    }
}
