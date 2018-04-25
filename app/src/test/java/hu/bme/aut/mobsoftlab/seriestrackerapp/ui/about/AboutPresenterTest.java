package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import hu.bme.aut.mobsoftlab.seriestrackerapp.DaggerSeriesTrackerApplicationComponent;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.IAboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.MockInteractorModule;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class AboutPresenterTest {

    @Before
    public void initMock() {
        IAboutInteractor interactor = Mockito.mock(IAboutInteractor.class);
        Mockito.when(interactor.getVersionName()).thenReturn("0.1.1-test");

        SeriesTrackerApplication.injector = DaggerSeriesTrackerApplicationComponent
                .builder()
                .interactorModule(new MockInteractorModule(interactor))
                .build();
    }

    @Test
    public void getVersionName_isCorrect() {
        // Given
        AboutScreen screen = Mockito.mock(AboutScreen.class);
        AboutPresenter presenter = new AboutPresenter();
        presenter.attachScreen(screen);

        // When
        presenter.getVersionName();

        // Then
        Mockito.verify(screen).showVersionName("0.1.1-test");
    }

    @Test
    public void navigateBack_isCorrect() {
        // Given
        AboutScreen screen = Mockito.mock(AboutScreen.class);
        AboutPresenter presenter = new AboutPresenter();
        presenter.attachScreen(screen);

        // When
        presenter.navigateBack();

        // Then
        Mockito.verify(screen).navigateBack();
    }
}
