package io.github.bretwitt.satviz.simulationstate.stateevents.onupdatestatevectorevent;

import io.github.bretwitt.engine.events.Event;

public class OnUpdateStateVectorEvent extends Event {

    OnUpdateStateVectorEventData data;

    public OnUpdateStateVectorEvent(OnUpdateStateVectorEventData data) {
        super(data);
        this.data = data;
    }

    public OnUpdateStateVectorEventData getUpdateData() {
        return data;
    }
}
