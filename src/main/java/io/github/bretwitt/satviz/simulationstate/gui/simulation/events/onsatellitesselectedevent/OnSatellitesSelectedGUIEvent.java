package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onsatellitesselectedevent;

import io.github.bretwitt.engine.events.Event;

public class OnSatellitesSelectedGUIEvent extends Event {

    private final OnSatellitesSelectedGUIEventData data;

    public OnSatellitesSelectedGUIEvent(OnSatellitesSelectedGUIEventData data) {
        super(data);
        this.data = data;
    }

    @Override
    public OnSatellitesSelectedGUIEventData getData() {
        return data;
    }


}
