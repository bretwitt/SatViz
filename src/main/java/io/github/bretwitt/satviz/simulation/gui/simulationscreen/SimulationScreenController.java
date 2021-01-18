package io.github.bretwitt.satviz.simulation.gui.simulationscreen;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.NiftyEventSubscriber;
import de.lessvoid.nifty.controls.ButtonClickedEvent;
import de.lessvoid.nifty.controls.ListBox;
import de.lessvoid.nifty.controls.ListBoxSelectionChangedEvent;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.addsatelliteprompt.AddSatellitePrompt;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.ScreenEventBus;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.events.OnSatelliteAddClickedEvent;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.satellitebox.SatelliteBox;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents.satellitebox.SatelliteListBox;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.events.OnSatelliteListBoxSelectionEvent;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;

import javax.annotation.Nonnull;

public class SimulationScreenController implements ScreenController {

    Screen screen;
    Nifty nifty;
    SatViz satViz;

    SatelliteListBox satelliteListBox;
    AddSatellitePrompt addSatellitePrompt;

    ScreenEventBus screenEventBus;
    StateEventBus stateEventBus;

    public SimulationScreenController(SatViz satViz, StateEventBus stateEventBus) {
        this.stateEventBus = stateEventBus;
        this.satViz = satViz;
        initializeEventBusses();
    }

    private void initializeEventBusses() {
        stateEventBus.register(this);
        screenEventBus = new ScreenEventBus();
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
        ListBox satelliteListBoxNiftyControl = screen.findNiftyControl("satelliteListBox", ListBox.class);
        satelliteListBox = new SatelliteListBox(satelliteListBoxNiftyControl, nifty, screenEventBus, stateEventBus, satViz);
        addSatellitePrompt = new AddSatellitePrompt(nifty, screenEventBus, stateEventBus);
    }

    @Override
    public void onEndScreen() {
    }

    @NiftyEventSubscriber(id="addSatelliteButton")
    public void onButtonClicked(String id, ButtonClickedEvent event){
        OnSatelliteAddClickedEvent clickedEvent = new OnSatelliteAddClickedEvent();
        screenEventBus.post(clickedEvent);
    }

    @NiftyEventSubscriber(id="satelliteListBox")
    public void onSatelliteListBoxSelection(String id, ListBoxSelectionChangedEvent<SatelliteBox> event) {
        OnSatelliteListBoxSelectionEvent selectionEvent = new OnSatelliteListBoxSelectionEvent(event.getSelection());
        screenEventBus.post(selectionEvent);
    }
}


