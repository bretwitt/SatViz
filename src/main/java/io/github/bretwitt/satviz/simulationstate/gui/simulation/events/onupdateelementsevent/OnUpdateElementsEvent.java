package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onupdateelementsevent;

import io.github.bretwitt.engine.events.Event;

public class OnUpdateElementsEvent extends Event {
    public OnUpdateElementsEvent(OnUpdateElementsData onUpdateElementsData) {
        super(onUpdateElementsData);
    }
}
