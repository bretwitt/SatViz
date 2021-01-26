package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onremovesatelliteguievent;

import io.github.bretwitt.engine.events.Event;

public class OnRemoveSatelliteGUIEvent extends Event {

    OnRemoveSatelliteGUIEventData data;

    public OnRemoveSatelliteGUIEvent(OnRemoveSatelliteGUIEventData data) {
        super(data);
        this.data = data;
    }

    @Override
    public OnRemoveSatelliteGUIEventData getData() {
        return data;
    }
}
