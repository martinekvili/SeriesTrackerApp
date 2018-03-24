package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about;

import hu.bme.aut.mobsoftlab.seriestrackerapp.BuildConfig;

public class AboutInteractor {

    public String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }
}
