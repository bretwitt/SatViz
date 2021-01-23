package io.github.bretwitt.satviz.simulationstate.stateevents.onsatellitelistupdateevent;

import io.github.bretwitt.engine.events.Event;

public class OnSatelliteListUpdateEvent extends Event {

    OnSatelliteListUpdateEventData data;

    public OnSatelliteListUpdateEvent(OnSatelliteListUpdateEventData data) {
        super(data);
        this.data = data;
    }
}
