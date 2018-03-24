package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named("NetworkExecutor")
    public Executor provideNetworkExecutor() {
        return Executors.newCachedThreadPool();
    }
}
