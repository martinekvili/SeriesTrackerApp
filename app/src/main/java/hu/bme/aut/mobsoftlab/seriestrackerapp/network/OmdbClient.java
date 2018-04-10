package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.api.BaseApi;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.ModelApiResponse;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SearchItem;
import retrofit2.Call;
import retrofit2.Response;

public class OmdbClient implements IOmdbClient {

    @Inject
    BaseApi apiClient;

    public OmdbClient() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    public EpisodeDetails getEpisodeDetails(String imdbID, int season, int episode) throws IOException {
        Call<ModelApiResponse> call = apiClient.get(imdbID, null, null, null, null, "short", "json", null, null, null, season, episode);
        ModelApiResponse apiResponse = getApiResponse(call);
        return new EpisodeDetails(apiResponse.getPlot(), apiResponse.getImdbRating());
    }

    @Override
    public SeasonsAndEpisodesCount getSeasonsAndEpisodesCount(String imdbID, int season) throws IOException {
        Call<ModelApiResponse> call = apiClient.get(imdbID, null, null, null, null, null, "json", null, null, null, season, null);
        ModelApiResponse apiResponse = getApiResponse(call);
        return new SeasonsAndEpisodesCount(apiResponse.getTotalSeasons(), apiResponse.getEpisodes().size());
    }

    @Override
    public List<SeriesSearchResult> getSeriesSearchResult(String prefix) throws IOException {
        Call<ModelApiResponse> call = apiClient.get(null, null, prefix + "*", "series", null, null, "json", null, null, null, null, null);
        ModelApiResponse apiResponse = getApiResponse(call);

        List<SeriesSearchResult> result = new ArrayList<>();
        for (SearchItem item : apiResponse.getSearch())
            result.add(new SeriesSearchResult(item.getImdbID(), item.getTitle(), item.getPoster()));

        return result;
    }

    private ModelApiResponse getApiResponse(Call<ModelApiResponse> call) throws IOException {
        Response<ModelApiResponse> response = call.execute();

        ModelApiResponse apiResponse = response.body();
        if (apiResponse == null)
            throw new IOException(response.message());

        if (!apiResponse.isResponse())
            throw new IOException(apiResponse.getError());

        return apiResponse;
    }
}
