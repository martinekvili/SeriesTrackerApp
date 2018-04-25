package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about;

import hu.bme.aut.mobsoftlab.seriestrackerapp.BuildConfig;

public class AboutInteractor implements IAboutInteractor {

    @Override
    public String getVersionName() {
        return BuildConfig.VERSION_NAME;
    }
}
