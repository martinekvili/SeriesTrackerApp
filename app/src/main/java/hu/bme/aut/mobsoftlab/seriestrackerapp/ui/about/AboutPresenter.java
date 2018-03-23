package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.AboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.Presenter;

public class AboutPresenter extends Presenter<AboutScreen> {

    @Inject
    AboutInteractor interactor;

    private String versionName;

    public AboutPresenter() {
        SeriesTrackerApplication.injector.inject(this);
    }

    public void getVersionName() {
        if (versionName == null)
            versionName = interactor.getVersionName();

        screen.showVersionName(versionName);
    }

    public void navigateBack() {
        screen.navigateBack();
    }
}
