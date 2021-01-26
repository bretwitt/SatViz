package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onaddsatelliteguievent;

import io.github.bretwitt.engine.events.Event;

public class OnAddSatelliteGUIEvent extends Event {

    OnAddSatelliteGUIEventData data;

    public OnAddSatelliteGUIEvent(OnAddSatelliteGUIEventData data) {
        super(data);
        this.data = data;
    }

    public OnAddSatelliteGUIEventData getData() {
        return data;
    }
}
