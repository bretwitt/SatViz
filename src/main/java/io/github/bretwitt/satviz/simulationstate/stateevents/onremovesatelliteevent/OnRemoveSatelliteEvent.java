package io.github.bretwitt.satviz.simulationstate.stateevents.onremovesatelliteevent;

import io.github.bretwitt.engine.events.Event;

public class OnRemoveSatelliteEvent extends Event {

    OnRemoveSatelliteEventData data;

    public OnRemoveSatelliteEvent(OnRemoveSatelliteEventData data) {
        super(data);
        this.data = data;
    }

    @Override
    public OnRemoveSatelliteEventData getData() {
        return data;
    }
}
