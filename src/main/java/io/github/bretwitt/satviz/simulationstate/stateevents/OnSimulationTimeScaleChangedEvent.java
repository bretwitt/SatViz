package io.github.bretwitt.satviz.simulationstate.stateevents;

import io.github.bretwitt.engine.events.Event;

public class OnSimulationTimeScaleChangedEvent extends Event {

    private final SimulationTimeScaleChangedEventData data;

    public OnSimulationTimeScaleChangedEvent(SimulationTimeScaleChangedEventData data) {
        super(data);
        this.data = data;
    }

    public SimulationTimeScaleChangedEventData getData() {
        return data;
    }
}
