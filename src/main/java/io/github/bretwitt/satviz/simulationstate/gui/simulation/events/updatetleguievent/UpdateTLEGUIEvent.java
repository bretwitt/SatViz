package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatetleguievent;

import io.github.bretwitt.engine.events.Event;

public class UpdateTLEGUIEvent extends Event {

    private UpdateTLEGUIEventData updateTLEGUIEventData;

    public UpdateTLEGUIEvent(UpdateTLEGUIEventData updateTLEGUIEventData) {
        super(updateTLEGUIEventData);
        this.updateTLEGUIEventData = updateTLEGUIEventData;
    }

    public UpdateTLEGUIEventData getData() {
        return updateTLEGUIEventData;
    }
}
