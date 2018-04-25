package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bme.aut.mobsoftlab.seriestrackerapp.GlideApp;
import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.EpisodeDetails;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class DetailsActivity extends AppCompatActivity implements DetailsScreen {

    public static final String SERIES_KEY = "SERIES_KEY";
    public static final String PRESENTER_STATE_KEY = "PRESENTER_STATE_KEY";

    @Inject
    DetailsPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.seriesPoster)
    ImageView seriesPoster;
    @BindView(R.id.seriesTitle)
    TextView seriesTitle;
    @BindView(R.id.seriesSeason)
    TextView seriesSeason;
    @BindView(R.id.seriesEpisode)
    TextView seriesEpisode;
    @BindView(R.id.btnSeen)
    Button btnSeen;
    @BindView(R.id.noMoreEpisodes)
    TextView noMoreEpisodes;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.descriptionTitle)
    TextView descriptionTitle;
    @BindView(R.id.seriesDescription)
    TextView seriesDescription;
    @BindView(R.id.ratingTitle)
    TextView ratingTitle;
    @BindView(R.id.seriesRating)
    TextView seriesRating;

    public DetailsActivity() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null)
            actionbar.setTitle(R.string.title_details);

        setPresenterState(savedInstanceState);

        btnSeen.setOnClickListener(v -> {
            showLoading(false);
            presenter.stepToNextEpisode();
        });
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

        showLoading(true);
        presenter.getSeriesDetails();
    }

    private void showLoading(boolean isFirst) {
        btnSeen.setVisibility(View.INVISIBLE);
        noMoreEpisodes.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        if (isFirst) {
            descriptionTitle.setVisibility(View.GONE);
            seriesDescription.setVisibility(View.GONE);

            ratingTitle.setVisibility(View.GONE);
            seriesRating.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSeries(SavedSeries series) {
        GlideApp.with(this)
                .load(series.getPosterUrl())
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(seriesPoster);
        seriesTitle.setText(series.getTitle());
        seriesSeason.setText(getString(R.string.series_season, series.getSeason()));
        seriesEpisode.setText(getString(R.string.series_episode, series.getEpisode()));
    }

    @Override
    public void showEpisodeDetails(EpisodeDetails details) {
        seriesDescription.setText(details.getPlot());
        seriesRating.setText(getString(R.string.series_rating, details.getImdbRating()));

        descriptionTitle.setVisibility(View.VISIBLE);
        seriesDescription.setVisibility(View.VISIBLE);

        ratingTitle.setVisibility(View.VISIBLE);
        seriesRating.setVisibility(View.VISIBLE);

        progressBar.setVisibility(View.GONE);
        if (details.isLastEpisode()) {
            btnSeen.setVisibility(View.INVISIBLE);
            noMoreEpisodes.setVisibility(View.VISIBLE);
        } else {
            noMoreEpisodes.setVisibility(View.GONE);
            btnSeen.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showNetworkErrorMessage(String errorMessage) {
        progressBar.setVisibility(View.GONE);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.network_error)
                .setMessage(errorMessage)
                .setPositiveButton(R.string.dialog_positive, (dialog1, which) -> finish())
                .create();

        dialog.show();
    }
}
