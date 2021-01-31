package io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent;

import io.github.bretwitt.engine.events.Event;

public class OnSimulationPauseEvent extends Event {
    SimulationPauseEventData data;
    public OnSimulationPauseEvent(SimulationPauseEventData data) {
        super(data);
        this.data = data;
    }

    public SimulationPauseEventData getData() {
        return data;
    }
}
