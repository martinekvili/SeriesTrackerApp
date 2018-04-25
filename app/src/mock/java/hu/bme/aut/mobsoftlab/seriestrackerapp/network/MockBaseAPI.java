package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.api.BaseApi;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.EpisodeData;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.ModelApiResponse;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SearchItem;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.model.SeriesData;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.mock.Calls;

public class MockBaseAPI implements BaseApi {

    private final Map<String, SeriesDataWithEpisodeCount> database;

    public MockBaseAPI() {
        this.database = new HashMap<>();

//        database.put("tt2861424", createSeriesData(
//                "tt2861424",
//                "Rick and Morty",
//                "https://images-na.ssl-images-amazon.com/images/M/MV5BMzhhNzY1MzUtYjE1OC00ZDVkLWE4Y2YtZmEzYWZiNTdiOGE1XkEyXkFqcGdeQXVyNjExODE1MDc@._V1_SX300.jpg",
//                "Rick brings Beth to a world he created for her when she was younger while Beth looks for a long-lost childhood friend trapped there for years.",
//                "8.2",
//                3));
//        episodeCount.put("tt2861424", 13);
//        database.put("tt0903747", createSeriesData(
//                "tt0903747",
//                "Breaking Bad",
//                "https://ia.media-imdb.com/images/M/MV5BZDNhNzhkNDctOTlmOS00NWNmLWEyODQtNWMxM2UzYmJiNGMyXkEyXkFqcGdeQXVyNTMxMjgxMzA@._V1_SX300.jpg",
//                "Walt and Jesse seek out an unlikely partner for a new business venture. The DEA follows up new leads in its investigation.",
//                "8.8",
//                6));
//        episodeCount.put("tt0903747", 13);
//        database.put("tt1486217", createSeriesData(
//                "tt1486217",
//                "Archer",
//                "https://ia.media-imdb.com/images/M/MV5BMTg3NTMwMzY2OF5BMl5BanBnXkFtZTgwMDcxMjQ0NDE@._V1_SX300.jpg",
//                "Archer's search for his father's identity by going to Russia puts him in hot water as he is held prisoner by Nikolai Jackov and Malory has to ask Barry Dillon to rescue him.",
//                "8.1",
//                3));
//        episodeCount.put("tt1486217", 17);
//        database.put("tt0063889", createSeriesData(
//                "tt0063889",
//                "Dastardly and Muttley in Their Flying Machines",
//                "https://images-na.ssl-images-amazon.com/images/M/MV5BMzhhNzY1MzUtYjE1OC00ZDVkLWE4Y2YtZmEzYWZiNTdiOGE1XkEyXkFqcGdeQXVyNjExODE1MDc@._V1_SX300.jpg",
//                "Kerry and Bridget are caught partying on the evening news while the family is vacationing at Cate's parents home in Florida. The news also runs an archived video that features a young Cate, who is very drunk at a bar.",
//                "7.2",
//                1));
//        episodeCount.put("tt0063889", 18);
    }

    public void add(SeriesDataWithEpisodeCount data) {
        database.put(data.getSeriesData().getImdbID(), data);
    }

    @Override
    public Call<Void> create(SeriesData content) {
        database.put(content.getImdbID(), new SeriesDataWithEpisodeCount(content, 0));
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
            return findByID(i, season);
        else if (s != null)
            return findByName(s.substring(0, s.length() - 1).toLowerCase());
        else
            throw new InvalidParameterException("You have to search either by IMDb ID or title.");
    }

    private Call<ModelApiResponse> findByID(String imdbID, Integer season) {
        ModelApiResponse response;

        if (database.containsKey(imdbID)) {
            SeriesDataWithEpisodeCount found = database.get(imdbID);
            response = MockNetworkUtils.createModelApiResponse(found.getSeriesData());

            if (season != null && found.getEpisodeCount() > 0) {
                List<EpisodeData> episodes = new ArrayList<>(found.getEpisodeCount());

                for (int i = 0; i < found.getEpisodeCount(); i++)
                    episodes.add(new EpisodeData());

                response.setEpisodes(episodes);
            }
        }
        else
            response = new ModelApiResponse().response(false).error("Could not find resource");

        return Calls.response(response);
    }

    private Call<ModelApiResponse> findByName(String prefix) {
        List<SearchItem> result = new ArrayList<>();
        for (SeriesDataWithEpisodeCount data : database.values()) {
            if (data.getSeriesData().getTitle().toLowerCase().startsWith(prefix))
                result.add(MockNetworkUtils.createSearchItem(data.getSeriesData()));
        }

        if (result.size() > 0)
            return Calls.response(new ModelApiResponse().response(true).search(result));
        else
            return Calls.response(new ModelApiResponse().response(false).error("No results"));
    }

    @Override
    public Call<Void> update(String imdbID, SeriesData content) {
        if (!database.containsKey(imdbID))
            return Calls.response(Response.error(404, ResponseBody.create(MediaType.parse("text/plain"), "Could not find resource")));

        database.put(imdbID, new SeriesDataWithEpisodeCount(content, 0));
        return Calls.response(Response.success(null));
    }
}
