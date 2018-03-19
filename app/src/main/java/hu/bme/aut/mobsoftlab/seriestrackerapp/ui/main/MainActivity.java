package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashSet;
import java.util.List;

import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MainPresenter.getInstance().attachScreen(this);
    }

    @Override
    protected void onStop() {
        MainPresenter.getInstance().detachScreen();
        super.onStop();
    }

    @Override
    public void showSavedSeries(List<SavedSeries> savedSeries) {
        // TODO
    }

    @Override
    public void showAddSeriesDialog(HashSet<String> alreadyAddedSeries) {
        // TODO
    }
}
