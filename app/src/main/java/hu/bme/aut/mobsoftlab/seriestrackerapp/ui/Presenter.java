package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

public abstract class Presenter<Screen> {
    protected Screen screen;

    public void attachScreen(Screen screen) {
        this.screen = screen;
    }

    public void detachScreen() {
        this.screen = null;
    }
}
