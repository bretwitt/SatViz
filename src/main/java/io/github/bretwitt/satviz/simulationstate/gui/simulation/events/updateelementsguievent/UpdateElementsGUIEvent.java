package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updateelementsguievent;

import io.github.bretwitt.engine.events.Event;

public class UpdateElementsGUIEvent extends Event {
    private UpdateElementsGUIEventData data;

    public UpdateElementsGUIEvent(UpdateElementsGUIEventData data) {
        super(data);
        this.data = data;
    }

    public UpdateElementsGUIEventData getElementData() {
        return data;
    }
}
