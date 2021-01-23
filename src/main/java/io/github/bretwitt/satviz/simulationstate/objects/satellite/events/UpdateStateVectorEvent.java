package io.github.bretwitt.satviz.simulationstate.objects.satellite.events;

import io.github.bretwitt.engine.events.Event;

public class UpdateStateVectorEvent extends Event {


    UpdateStateVectorData data;

    public UpdateStateVectorEvent(UpdateStateVectorData data) {
        super(data);
        this.data = data;
    }

    public UpdateStateVectorData getUpdateStateVectorData() {
        return data;
    }


}
