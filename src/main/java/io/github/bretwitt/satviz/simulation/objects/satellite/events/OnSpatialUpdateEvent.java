package io.github.bretwitt.satviz.simulation.objects.satellite.events;

import io.github.bretwitt.engine.events.Event;

public class OnSpatialUpdateEvent  extends Event {
    public OnSpatialUpdateEvent(Object data) {
        super(data);
    }
}

class SpatialUpdateData {
}