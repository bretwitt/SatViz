package io.github.bretwitt.satviz.simulation.gui.simulationscreen.guicomponents;

import de.lessvoid.nifty.Nifty;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.satviz.simulation.gui.simulationscreen.screeneventbus.ScreenEventBus;

public class GuiComponent {

    Nifty nifty;
    ScreenEventBus screenEventBus;
    StateEventBus stateEventBus;

    public GuiComponent(Nifty nifty, ScreenEventBus screenEventBus, StateEventBus stateEventBus){
        this.screenEventBus = screenEventBus;
        this.stateEventBus = stateEventBus;
        this.nifty = nifty;
        screenEventBus.register(this);
    }

    public Nifty getNifty() { return nifty; }

    public ScreenEventBus getScreenEventBus() {
        return screenEventBus;
    }

    public StateEventBus getStateEventBus() {
        return stateEventBus;
    }
}
