package io.github.bretwitt.satviz.simulationstate.stateevents.onaddsatelliteevent;

public class OnAddSatelliteEvent {

    OnAddSatelliteEventData data;
    public OnAddSatelliteEvent(OnAddSatelliteEventData onAddSatelliteEventData) {
        this.data = onAddSatelliteEventData;
    }

    public OnAddSatelliteEventData getData() {
        return data;
    }
}
