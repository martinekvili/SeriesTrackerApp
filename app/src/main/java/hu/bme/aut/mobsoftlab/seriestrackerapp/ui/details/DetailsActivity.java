package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    public static final String SERIES_KEY = "SERIES_KEY";
    public static final String PRESENTER_STATE_KEY = "PRESENTER_STATE_KEY";

    @Inject
    DetailsPresenter presenter;

    public DetailsActivity() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setPresenterState(savedInstanceState);
    }

    private void setPresenterState(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            presenter.setState(savedInstanceState.getParcelable(PRESENTER_STATE_KEY));
        else if (getIntent() != null)
            presenter.setState(new DetailsPresenterState(getIntent().getParcelableExtra(SERIES_KEY)));
        else
            throw new IllegalStateException("Activity must have either intent extra or saved state");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PRESENTER_STATE_KEY, presenter.getState());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        presenter.detachScreen();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getSeriesDetails();
    }

    @Override
    public void showSeries(SavedSeries series) {
        // TODO set UI
    }

    @Override
    public void showEpisodeDetails(EpisodeDetails details) {
        // TODO set UI
    }

    @Override
    public void navigateBack() {
        finish();
    }

    @Override
    public void showNetworkErrorMessage(String errorMessage) {
        // TODO set UI
    }
}
