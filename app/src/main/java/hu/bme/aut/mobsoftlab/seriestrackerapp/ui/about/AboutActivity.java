package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;

public class AboutActivity extends AppCompatActivity implements AboutScreen {

    @Inject
    AboutPresenter presenter;

    public AboutActivity() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
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
        presenter.getVersionName();
    }

    @Override
    public void showVersionName(String versionName) {
        // TODO
    }

    @Override
    public void navigateBack() {
        finish();
    }
}
