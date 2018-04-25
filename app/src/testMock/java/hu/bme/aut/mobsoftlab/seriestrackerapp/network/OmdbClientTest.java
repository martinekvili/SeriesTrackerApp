package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class OmdbClientTest {

    @Before
    public void initMockApi() {
        NetworkModule.baseAPI.add(new SeriesDataWithEpisodeCount(
                "tt2861424",
                "Rick and Morty",
                "_dummy_",
                "_dummy_",
                "8.2",
                3,
                10));
        NetworkModule.baseAPI.add(new SeriesDataWithEpisodeCount(
                "tt0903747",
                "Breaking Bad",
                "_dummy_",
                "_dummy_",
                "8.8",
                6,
                13));
        NetworkModule.baseAPI.add(new SeriesDataWithEpisodeCount(
                "tt1486217",
                "Richie Rich",
                "_dummy_",
                "_dummy_",
                "8.1",
                3,
                17));
    }

    @Test
    public void getEpisodeDetails_isCorrect() throws IOException {
        // Given
        IOmdbClient client = new OmdbClient();

        // When
        EpisodeDetails result = client.getEpisodeDetails("tt2861424", 2, 10);

        // Then
        Assert.assertEquals("_dummy_", result.getPlot());
        Assert.assertEquals("8.2", result.getImdbRating());
    }

    @Test(expected = IOException.class)
    public void getEpisodeDetails_notFound() throws IOException {
        // Given
        IOmdbClient client = new OmdbClient();

        // When
        client.getEpisodeDetails("tt2861425", 2, 10);
    }

    @Test
    public void getSeasonsAndEpisodesCount_isCorrect() throws IOException {
        // Given
        IOmdbClient client = new OmdbClient();

        // When
        SeasonsAndEpisodesCount result = client.getSeasonsAndEpisodesCount("tt2861424", 2);

        // Then
        Assert.assertEquals(3, result.getTotalSeasons());
        Assert.assertEquals(10, result.getEpisodesInSeason());
    }

    @Test(expected = IOException.class)
    public void getSeasonsAndEpisodesCount_notFound() throws IOException {
        // Given
        IOmdbClient client = new OmdbClient();

        // When
        client.getSeasonsAndEpisodesCount("tt2861425", 2);
    }

    @Test
    public void getSeriesSearchResult_isCorrect() throws IOException {
        // Given
        IOmdbClient client = new OmdbClient();

        // When
        List<SeriesSearchResult> result = client.getSeriesSearchResult("ric");

        // Then
        Assert.assertEquals(2, result.size());
        for (SeriesSearchResult ssr : result)
            Assert.assertTrue(ssr.getImdbID().equals("tt2861424") || ssr.getImdbID().equals("tt1486217"));
        Assert.assertFalse(result.get(0).getImdbID().equals(result.get(1).getImdbID()));
    }

    @Test
    public void getSeriesSearchResult_emptyResult() throws IOException {
        // Given
        IOmdbClient client = new OmdbClient();

        // When
        List<SeriesSearchResult> result = client.getSeriesSearchResult("asdf");

        // Then
        Assert.assertEquals(0, result.size());
    }

}
