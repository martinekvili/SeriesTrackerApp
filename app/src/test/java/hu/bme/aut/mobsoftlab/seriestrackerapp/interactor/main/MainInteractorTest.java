package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main;

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
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.InteractorTestBase;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetAlreadyAddedSeriesIDsEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.main.event.GetSeriesListEvent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class MainInteractorTest extends InteractorTestBase<MainInteractor> {

    @Before
    public void initMock() throws InstantiationException, IllegalAccessException {
        super.initMock(MainInteractor.class);
    }

    @Test
    public void getSeriesList_isCorrect() {
        // Given
        List<SavedSeries> seriesList = new ArrayList<>();
        seriesList.add(new SavedSeries("tt2861424", "Rick and Morty", "_dummy_", 3, 9));
        seriesList.add(new SavedSeries("tt0903747", "Breaking Bad", "_dummy_", 5, 12));

        Mockito.when(dal.getSavedSeries()).thenReturn(seriesList);

        // When
        interactor.getSeriesList();

        // Then
        eventSender.send(new GetSeriesListEvent(seriesList));
    }

    @Test
    public void getAlreadyAddedSeriesIDs_isCorrect() {
        // Given
        Set<String> ids = new HashSet<>();
        ids.add("tt2861424");
        ids.add("tt0903747");

        Mockito.when(dal.getAlreadyAddedSeriesIDs()).thenReturn(ids);

        // When
        interactor.getAlreadyAddedSeriesIDs();

        // Then
        eventSender.send(new GetAlreadyAddedSeriesIDsEvent(ids));
    }
}
