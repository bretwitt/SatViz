package io.github.bretwitt.satviz.simulationstate.objects.satellite.events;

import io.github.bretwitt.engine.events.Event;

public class OnSpatialUpdateEvent  extends Event {
    public OnSpatialUpdateEvent(Object data) {
        super(data);
    }
}

class SpatialUpdateData {
}