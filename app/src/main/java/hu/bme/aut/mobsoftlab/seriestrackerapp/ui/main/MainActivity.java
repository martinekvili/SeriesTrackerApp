package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about.AboutActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details.DetailsActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries.NewSeriesDialog;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter presenter;

    public MainActivity() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button details = findViewById(R.id.btnDetails);
        details.setOnClickListener(v -> presenter.selectSeries(new SavedSeries(null, null, null, 0, 0)));
        Button dialog = findViewById(R.id.btnDialog);
        dialog.setOnClickListener(v -> presenter.addNewSeriesDialog());
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
        presenter.getSeriesList();
    }

    @Override
    public void showSeriesList(List<SavedSeries> savedSeries) {
        // TODO set UI
    }

    @Override
    public void showSeriesDetailsPage(SavedSeries savedSeries) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.SERIES_KEY, savedSeries);
        startActivity(intent);
    }

    @Override
    public void showAddSeriesDialog(Set<String> alreadyAddedSeries) {
        NewSeriesDialog dialog = NewSeriesDialog.newInstance(alreadyAddedSeries);
        dialog.show(getSupportFragmentManager(), "NewSeriesDialogFragment");
    }

    @Override
    public void showAboutPage() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
