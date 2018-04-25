package hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about;

import org.junit.Assert;
import org.junit.Test;

import hu.bme.aut.mobsoftlab.seriestrackerapp.BuildConfig;

public class AboutInteractorTest {

    @Test
    public void getVersionName_isCorrect() {
        // Given
        AboutInteractor interactor = new AboutInteractor();

        // When
        String versionName = interactor.getVersionName();

        // Then
        Assert.assertEquals(BuildConfig.VERSION_NAME, versionName);
    }

}
