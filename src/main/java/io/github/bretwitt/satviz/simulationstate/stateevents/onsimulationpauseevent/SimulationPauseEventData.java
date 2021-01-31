package io.github.bretwitt.satviz.simulationstate.stateevents.onsimulationpauseevent;

public class SimulationPauseEventData {

    private final boolean paused;

    public SimulationPauseEventData(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() {
        return paused;
    }
}
