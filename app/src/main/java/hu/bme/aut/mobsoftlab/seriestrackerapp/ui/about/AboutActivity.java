package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;

public class AboutActivity extends AppCompatActivity implements AboutScreen {

    @Inject
    AboutPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.aboutVersionText)
    TextView versionText;

    MenuItem aboutMenuItem;

    public AboutActivity() {
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setTitle(R.string.title_about);
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        aboutMenuItem = navigationView.getMenu().findItem(R.id.nav_about);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            // set item as selected to persist highlight
            menuItem.setChecked(true);
            // close drawer when item is tapped
            drawerLayout.closeDrawers();

            if (menuItem.getItemId() == R.id.nav_main)
                presenter.navigateBack();

            return true;
        });
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
        aboutMenuItem.setChecked(true);
        presenter.getVersionName();
    }

    @Override
    public void showVersionName(String versionName) {
        versionText.setText(getString(R.string.version_text, versionName));
    }

    @Override
    public void navigateBack() {
        finish();
    }
}
