package hu.bme.aut.mobsoftlab.seriestrackerapp.ui;

import org.greenrobot.eventbus.EventBus;

public abstract class PresenterWithEvents<Screen> extends Presenter<Screen> {

    @Override
    public void attachScreen(Screen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }
}
