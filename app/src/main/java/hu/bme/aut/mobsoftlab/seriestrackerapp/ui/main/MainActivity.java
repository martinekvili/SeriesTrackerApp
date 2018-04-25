package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity implements MainScreen, NewSeriesDialog.DialogDismissedListener {

    @Inject
    MainPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.emptyRecyclerView)
    TextView emptyRecyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private SeriesAdapter seriesAdapter;

    private MenuItem mainMenuItem;

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
        if (actionbar != null) {
            actionbar.setTitle(R.string.title_main);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mainMenuItem = navigationView.getMenu().findItem(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // set item as selected to persist highlight
            menuItem.setChecked(true);
            // close drawer when item is tapped
            drawerLayout.closeDrawers();

            if (menuItem.getItemId() == R.id.nav_about)
                presenter.selectAboutPage();

            return true;
        });

        fab.setOnClickListener(v -> presenter.addNewSeriesDialog());

        recyclerView.setHasFixedSize(true);
        seriesAdapter = new SeriesAdapter(selectedSeries -> presenter.selectSeries(selectedSeries));
        recyclerView.setAdapter(seriesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

        mainMenuItem.setChecked(true);
        reload();
    }

    private void reload() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyRecyclerView.setVisibility(View.GONE);

        presenter.getSeriesList();
    }

    @Override
    public void showSeriesList(List<SavedSeries> savedSeries) {
        seriesAdapter.setSeries(savedSeries);

        progressBar.setVisibility(View.GONE);

        if (savedSeries.size() == 0) {
            recyclerView.setVisibility(View.GONE);
            emptyRecyclerView.setVisibility(View.VISIBLE);
        } else {
            emptyRecyclerView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
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
    public void onDialogDismissed() {
        reload();
    }

    @Override
    public void showAboutPage() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
