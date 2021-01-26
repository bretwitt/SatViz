package io.github.bretwitt.satviz.simulationstate.stateevents.onupdatetleevent;

public class UpdateTLEEvent {
    private UpdateTLEEventData updateTLEEventData;

    public UpdateTLEEvent(UpdateTLEEventData updateTLEEventData) {
        this.updateTLEEventData = updateTLEEventData;
    }

    public UpdateTLEEventData getData() {
        return updateTLEEventData;
    }
}
