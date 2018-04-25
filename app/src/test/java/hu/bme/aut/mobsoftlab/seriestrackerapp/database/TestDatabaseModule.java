package hu.bme.aut.mobsoftlab.seriestrackerapp.database;

import java.util.concurrent.Executor;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Provides;
import hu.bme.aut.mobsoftlab.seriestrackerapp.DirectExecutor;

public class TestDatabaseModule extends DatabaseModule {

    private final ISavedSeriesDAL dal;

    public TestDatabaseModule(ISavedSeriesDAL dal) {
        this.dal = dal;
    }

    @Provides
    @Singleton
    @Named("DatabaseExecutor")
    @Override
    public Executor provideDatabaseExecutor() {
        return new DirectExecutor();
    }

    @Provides
    @Singleton
    @Override
    public ISavedSeriesDAL provideSavedSeriesDAL() {
        return dal;
    }
}
