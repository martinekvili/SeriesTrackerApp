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

    public static MockBaseAPI baseAPI = new MockBaseAPI();

    @Provides
    @Singleton
    @Named("NetworkExecutor")
    public Executor provideNetworkExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    public BaseApi provideBaseApi() {
        return baseAPI;
    }

    @Provides
    @Singleton
    public IOmdbClient provideOmdbClient() {
        return new OmdbClient();
    }
}
