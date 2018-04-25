package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;
import java.security.InvalidParameterException;

import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.api.BaseApi;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.ModelApiResponse;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SeriesData;
import retrofit2.Call;

public class MockBaseAPITest {

    @Test
    public void getByID_isCorrect() throws IOException {
        // Given
        MockBaseAPI api = new MockBaseAPI();
        api.add(new SeriesDataWithEpisodeCount(
                "tt2861424",
                "Rick and Morty",
                "_dummy_",
                "_dummy_",
                "8.2",
                3,
                10));

        // When
        Call<ModelApiResponse> response = api.get("tt2861424", null, null, null, null, null, "json", null, null, null, 2, null);
        ModelApiResponse result = response.execute().body();

        // Then
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isResponse());
        Assert.assertEquals("Rick and Morty", result.getTitle());
        Assert.assertEquals(10, result.getEpisodes().size());
    }

    @Test
    public void getByID_isNotFound() throws IOException {
        // Given
        MockBaseAPI api = new MockBaseAPI();
        api.add(new SeriesDataWithEpisodeCount(
                "tt2861424",
                "Rick and Morty",
                "_dummy_",
                "_dummy_",
                "8.2",
                3,
                10));

        // When
        Call<ModelApiResponse> response = api.get("tt2861425", null, null, null, null, null, "json", null, null, null, 2, null);
        ModelApiResponse result = response.execute().body();

        // Then
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isResponse());
        Assert.assertNotNull(result.getError());
    }

    @Test(expected = InvalidParameterException.class)
    public void getByElse_throws() {
        // Given
        BaseApi api = new MockBaseAPI();

        // When
        api.get(null, null, null, null, null, null, "json", null, null, null, null, null);
    }

    @Test
    public void update_isCorrect() throws IOException {
        // Given
        SeriesData data = new SeriesData()
                .imdbID("tt2861424")
                .title("Rick and Morty")
                .poster("_dummy_")
                .plot("_dummy")
                .imdbRating("8.2")
                .totalSeasons(3);
        BaseApi api = new MockBaseAPI();
        api.create(data).execute();

        // When
        data.title("Something else");
        Assert.assertTrue(api.update("tt2861424", data).execute().isSuccessful());
        Call<ModelApiResponse> response = api.get("tt2861424", null, null, null, null, null, "json", null, null, null, null, null);
        ModelApiResponse result = response.execute().body();

        // Then
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isResponse());
        Assert.assertEquals("Something else", result.getTitle());
    }

    @Test
    public void update_notFound() throws IOException {
        // Given
        SeriesData data = new SeriesData()
                .imdbID("tt2861424")
                .title("Rick and Morty")
                .poster("_dummy_")
                .plot("_dummy")
                .imdbRating("8.2")
                .totalSeasons(3);
        BaseApi api = new MockBaseAPI();
        api.create(data).execute();

        // When
        data.title("Something else");
        Assert.assertFalse(api.update("tt2861425", data).execute().isSuccessful());
    }

    @Test
    public void delete_isCorrect() throws IOException {
        // Given
        SeriesData data = new SeriesData()
                .imdbID("tt2861424")
                .title("Rick and Morty")
                .poster("_dummy_")
                .plot("_dummy")
                .imdbRating("8.2")
                .totalSeasons(3);
        BaseApi api = new MockBaseAPI();
        api.create(data).execute();

        // When
        Assert.assertTrue(api.delete("tt2861424").execute().isSuccessful());
        Call<ModelApiResponse> response = api.get("tt2861424", null, null, null, null, null, "json", null, null, null, null, null);
        ModelApiResponse result = response.execute().body();

        // Then
        Assert.assertNotNull(result);
        Assert.assertFalse(result.isResponse());
        Assert.assertNotNull(result.getError());
    }

    @Test
    public void delete_notFound() throws IOException {
        // Given
        SeriesData data = new SeriesData()
                .imdbID("tt2861424")
                .title("Rick and Morty")
                .poster("_dummy_")
                .plot("_dummy")
                .imdbRating("8.2")
                .totalSeasons(3);
        BaseApi api = new MockBaseAPI();
        api.create(data).execute();

        // When
        Assert.assertFalse(api.delete("tt2861425").execute().isSuccessful());
    }
}
