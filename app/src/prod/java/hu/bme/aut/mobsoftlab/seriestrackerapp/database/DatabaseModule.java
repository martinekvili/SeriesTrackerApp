package hu.bme.aut.mobsoftlab.seriestrackerapp.database;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    @Named("DatabaseExecutor")
    public Executor provideDatabaseExecutor() {
        return Executors.newCachedThreadPool();
    }

    @Provides
    @Singleton
    public ISavedSeriesDAL provideSavedSeriesDAL() {
        return new SavedSeriesDAL();
    }
}
