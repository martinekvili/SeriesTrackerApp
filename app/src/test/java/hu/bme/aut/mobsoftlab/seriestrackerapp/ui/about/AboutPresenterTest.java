package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.about;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.TestSeriesTrackerApplicationHelper;
import hu.bme.aut.mobsoftlab.seriestrackerapp.interactor.about.IAboutInteractor;
import hu.bme.aut.mobsoftlab.seriestrackerapp.ui.TestInteractorModule;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE, application = SeriesTrackerApplication.class)
public class AboutPresenterTest {

    private IAboutInteractor interactor;
    private AboutScreen screen;
    private AboutPresenter presenter;

    @Before
    public void initMock() {
        interactor = Mockito.mock(IAboutInteractor.class);
        TestSeriesTrackerApplicationHelper.createTestInjector(new TestInteractorModule(interactor));

        screen = Mockito.mock(AboutScreen.class);

        presenter = new AboutPresenter();
        presenter.attachScreen(screen);
    }

    @Test
    public void getVersionName_isCorrect() {
        // Given
        Mockito.when(interactor.getVersionName()).thenReturn("0.1.1-test");

        // When
        presenter.getVersionName();

        // Then
        Mockito.verify(screen).showVersionName("0.1.1-test");
    }

    @Test
    public void navigateBack_isCorrect() {
        // When
        presenter.navigateBack();

        // Then
        Mockito.verify(screen).navigateBack();
    }
}
