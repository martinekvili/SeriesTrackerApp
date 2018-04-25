package hu.bme.aut.mobsoftlab.seriestrackerapp.network;

import java.util.concurrent.Executor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.DirectExecutor;

public class TestNetworkModule extends NetworkModule {

    @Provides
    @Singleton
    @Named("NetworkExecutor")
    @Override
    public Executor provideNetworkExecutor() {
        return new DirectExecutor();
    }
}
