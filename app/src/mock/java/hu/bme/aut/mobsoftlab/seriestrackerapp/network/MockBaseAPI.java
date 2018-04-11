package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.api.BaseApi;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.ModelApiResponse;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SeriesData;
import retrofit2.Call;

public class MockBaseAPI implements BaseApi {
    @Override
    public Call<Void> create(SeriesData content) {
        return null;
    }

    @Override
    public Call<Void> delete(String imdbID) {
        return null;
    }

    @Override
    public Call<ModelApiResponse> get(String i, String t, String s, String type, Integer y, String plot, String r, Integer page, String paramCallback, Integer v, Integer season, Integer episode) {
        return null;
    }

    @Override
    public Call<Void> update(String imdbID, SeriesData content) {
        return null;
    }
}
