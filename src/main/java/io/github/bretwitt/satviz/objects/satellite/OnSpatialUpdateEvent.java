package io.github.bretwitt.satviz.objects.satellite;

import io.github.bretwitt.engine.events.Event;

public class OnSpatialUpdateEvent  extends Event {
    public OnSpatialUpdateEvent(Object data) {
        super(data);
    }
}

class SpatialUpdateData {
}