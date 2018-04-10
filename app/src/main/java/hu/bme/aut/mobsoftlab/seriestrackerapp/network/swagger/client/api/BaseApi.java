package hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.api;

import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.ModelApiResponse;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SeriesData;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface BaseApi {
    /**
     * Create a new movie resource
     * Adds a new movie or series to the database.
     *
     * @param content The movie or the series to add. (required)
     * @return Call&lt;Void&gt;
     */
    @Headers({
            "Content-Type:application/json"
    })
    @POST("")
    Call<Void> create(
            @retrofit2.http.Body SeriesData content
    );

    /**
     * Delete a movie resource
     * Delete a movie or series from the database.
     *
     * @param imdbID A valid IMDb ID (e.g. tt1285016) (required)
     * @return Call&lt;Void&gt;
     */
    @Headers({
            "Content-Type:application/json"
    })
    @DELETE("{imdbID}")
    Call<Void> delete(
            @retrofit2.http.Path("imdbID") String imdbID
    );

    /**
     * Find a movie resource
     * Find movies or series by ID, Title or by Search. _Can only use one type of find at a time (by ID, by Title or by Search)._
     *
     * @param i             A valid IMDb ID (e.g. tt1285016) (optional)
     * @param t             Movie title to search for. (optional)
     * @param s             Movie title to search for. (optional)
     * @param type          Type of result to return. (optional)
     * @param y             Year of release. (optional)
     * @param plot          Return short or full plot. (optional, default to short)
     * @param r             The data type to return. (optional, default to json)
     * @param page          Page number to return. (optional, default to 1)
     * @param paramCallback JSONP callback name. (optional)
     * @param v             API version (reserved for future use). (optional, default to 1)
     * @param season        The season of the series to query. (optional)
     * @param episode       The episode of the series to query. (optional)
     * @return Call&lt;ModelApiResponse&gt;
     */
    @Headers({
            "Content-Type:application/json"
    })
    @GET("")
    Call<ModelApiResponse> get(
            @retrofit2.http.Query("i") String i, @retrofit2.http.Query("t") String t, @retrofit2.http.Query("s") String s, @retrofit2.http.Query("type") String type, @retrofit2.http.Query("y") Integer y, @retrofit2.http.Query("plot") String plot, @retrofit2.http.Query("r") String r, @retrofit2.http.Query("page") Integer page, @retrofit2.http.Query("callback") String paramCallback, @retrofit2.http.Query("v") Integer v, @retrofit2.http.Query("season") Integer season, @retrofit2.http.Query("episode") Integer episode
    );

    /**
     * Update a movie resource
     * Modify attributes of an already existing movie or series.
     *
     * @param imdbID  A valid IMDb ID (e.g. tt1285016) (required)
     * @param content The movie or the series to modify. (required)
     * @return Call&lt;Void&gt;
     */
    @Headers({
            "Content-Type:application/json"
    })
    @PUT("{imdbID}")
    Call<Void> update(
            @retrofit2.http.Path("imdbID") String imdbID, @retrofit2.http.Body SeriesData content
    );

}
