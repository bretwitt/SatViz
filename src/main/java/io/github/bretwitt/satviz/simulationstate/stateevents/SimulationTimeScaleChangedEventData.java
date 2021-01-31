package io.github.bretwitt.satviz.simulationstate.stateevents;

public class SimulationTimeScaleChangedEventData {

    private final float timeScale;

    public SimulationTimeScaleChangedEventData(float timeScale) {

        this.timeScale = timeScale;
    }

    public float getTimeScale() {
        return timeScale;
    }
}
