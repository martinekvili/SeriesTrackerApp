package hu.bme.aut.mobsoftlab.seriestrackerapp.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class MockSavedSeriesDALTest {

    private SavedSeries[] seriesInDatabase;
    private ISavedSeriesDAL savedSeriesDAL;

    @Before
    public void initDatabase() {
        seriesInDatabase = new SavedSeries[]{
                new SavedSeries(
                        "tt0063889",
                        "Dastardly and Muttley in Their Flying Machines",
                        "_dummy_",
                        1,
                        13),
                new SavedSeries(
                        "tt2861425",
                        "Rick and Morty",
                        "_dummy_",
                        3,
                        9),
                new SavedSeries(
                        "tt0903747",
                        "Breaking Bad",
                        "_dummy_",
                        5,
                        2),
                new SavedSeries(
                        "tt1486217",
                        "Archer",
                        "_dummy_",
                        2,
                        12)
        };

        savedSeriesDAL = new MockSavedSeriesDAL();
        for (SavedSeries series : seriesInDatabase)
            savedSeriesDAL.addSavedSeries(series);
    }

    @Test
    public void getSavedSeries_isCorrect() {
        // When
        List<SavedSeries> result = savedSeriesDAL.getSavedSeries();

        // Then
        Arrays.sort(seriesInDatabase, (a, b) -> a.getTitle().compareTo(b.getTitle()));
        Assert.assertArrayEquals(seriesInDatabase, result.toArray(new SavedSeries[]{}));
    }

    @Test
    public void getAlreadyAddedSeriesIDs_isCorrect() {
        // When
        Set<String> result = savedSeriesDAL.getAlreadyAddedSeriesIDs();

        // Then
        Assert.assertEquals(seriesInDatabase.length, result.size());
        for (SavedSeries series : seriesInDatabase)
            Assert.assertTrue(result.contains(series.getImdbID()));
    }

    @Test
    public void updateSavedSeries_isCorrect() {
        // Given
        SavedSeries updater = new SavedSeries(
                "tt0903747",
                "Breaking Bad",
                "_dummy_",
                6,
                12);

        // When
        savedSeriesDAL.updateSavedSeries(updater);
        List<SavedSeries> result = savedSeriesDAL.getSavedSeries();

        // Then
        Assert.assertFalse(result.indexOf(updater) == -1);
    }
}
