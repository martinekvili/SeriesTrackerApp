package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import java.util.HashMap;
import java.util.Map;

import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.api.BaseApi;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.ModelApiResponse;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SeriesData;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.Calls;

public class MockBaseAPI implements BaseApi {

    private final Map<String, SeriesData> database;

    public MockBaseAPI() {
        this.database = new HashMap<>();
    }

    @Override
    public Call<Void> create(SeriesData content) {
        database.put(content.getImdbID(), content);
        return Calls.response(Response.success(null));
    }

    @Override
    public Call<Void> delete(String imdbID) {
        if (!database.containsKey(imdbID))
            return Calls.response(Response.error(404, ResponseBody.create(MediaType.parse("text/plain"), "Could not find resource")));

        database.remove(imdbID);
        return Calls.response(Response.success(null));
    }

    @Override
    public Call<ModelApiResponse> get(String i, String t, String s, String type, Integer y, String plot, String r, Integer page, String paramCallback, Integer v, Integer season, Integer episode) {
        if (i != null)
            return findByID(i);

        else
            return null;
    }

    private Call<ModelApiResponse> findByID(String imdbID) {
        ModelApiResponse response;

        if (database.containsKey(imdbID))
            response = MockNetworkUtils.createModelApiResponse(database.get(imdbID));
        else
            response = new ModelApiResponse().response(false).error("Could not find resource");

        return Calls.response(response);
    }

    @Override
    public Call<Void> update(String imdbID, SeriesData content) {
        if (!database.containsKey(imdbID))
            return Calls.response(Response.error(404, ResponseBody.create(MediaType.parse("text/plain"), "Could not find resource")));

        database.put(imdbID, content);
        return Calls.response(Response.success(null));
    }
}
