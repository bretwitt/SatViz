package io.github.bretwitt.satviz.simulationstate.objects.satellite.events;

import com.jme3.scene.Spatial;
import io.github.bretwitt.engine.events.Event;

public class OnSatelliteSpatialInitializedEvent extends Event {
    public OnSatelliteSpatialInitializedEvent(Spatial data) {
        super(data);
    }
}
