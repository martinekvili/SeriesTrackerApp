package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter mainPresenter;

    public MainActivity() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        mainPresenter.detachScreen();
        super.onStop();
    }

    @Override
    public void showSeriesList(List<SavedSeries> savedSeries) {
        // TODO
    }

    @Override
    public void showSeriesDetailsPage(SavedSeries savedSeries) {
        // TODO
    }

    @Override
    public void showAddSeriesDialog(Set<String> alreadyAddedSeries) {
        // TODO
    }

    @Override
    public void showAboutPage() {
        // TODO
    }
}
