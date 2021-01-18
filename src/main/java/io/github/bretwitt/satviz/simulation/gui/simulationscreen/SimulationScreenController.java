package io.github.bretwitt.satviz.simulation.gui.simulationscreen;

import com.google.common.eventbus.EventBus;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.SatelliteListBox.satellitebox.OnSatelliteAddClickedEvent;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.SatelliteListBox.satellitebox.SatelliteBox;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.SatelliteListBox.satellitebox.SatelliteListBox;

import javax.annotation.Nonnull;

public class SimulationScreenController implements ScreenController {

    Screen screen;
    SatViz satViz;

    SatelliteListBox box;

    EventBus screenEventBus;
    EventBus stateEventBus;

    public SimulationScreenController(SatViz satViz, EventBus stateEventBus) {
        this.stateEventBus = stateEventBus;
        this.satViz = satViz;

        initializeEventBusses();
    }

    private void initializeEventBusses() {
        stateEventBus.register(this);
        screenEventBus = new EventBus();
    }

    @Override
    public void bind(@Nonnull Nifty nifty, @Nonnull Screen screen) {
        this.screen = screen;
    }

    @Override
    public void onStartScreen() {
        initScreenElements();
    }

    public void initScreenElements() {
        ListBox<SatelliteBox> satelliteListBoxControl = screen.findNiftyControl("satelliteListBox", ListBox.class);
        box = new SatelliteListBox(satelliteListBoxControl, screenEventBus, stateEventBus, satViz);
    }

    @Override
    public void onEndScreen() {
    }

    @NiftyEventSubscriber(id="addSatelliteButton")
    public void onButtonClicked(String id, ButtonClickedEvent event){
        OnSatelliteAddClickedEvent clickedEvent = new OnSatelliteAddClickedEvent();
        screenEventBus.post(clickedEvent);
    }
}

