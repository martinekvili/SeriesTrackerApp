package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about.AboutActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details.DetailsActivity;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries.NewSeriesDialog;

public class MainActivity extends AppCompatActivity implements MainScreen {

    @Inject
    MainPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    public MainActivity() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(R.string.title_main);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // set item as selected to persist highlight
            menuItem.setChecked(true);
            // close drawer when item is tapped
            drawerLayout.closeDrawers();

            if (menuItem.getItemId() == R.id.nav_about)
                presenter.selectAboutPage();

            return true;
        });

        Button details = findViewById(R.id.btnDetails);
        details.setOnClickListener(v -> presenter.selectSeries(new SavedSeries("tt0460649", "How I Met Your Mother", null, 9, 24)));
        Button dialog = findViewById(R.id.btnDialog);
        dialog.setOnClickListener(v -> presenter.addNewSeriesDialog());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
