package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.ApiClient;
import hu.bme.aut.mobsoftlab.seriestrackerapp.network.swagger.client.api.BaseApi;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named("NetworkExecutor")
    public Executor provideNetworkExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    public BaseApi provideBaseApi() {
        ApiClient apiClient = new ApiClient("api_key", "84463b02");
        return apiClient.createService(BaseApi.class);
    }

    @Provides
    @Singleton
    public IOmdbClient provideOmdbClient() {
        return new OmdbClient();
    }
}
