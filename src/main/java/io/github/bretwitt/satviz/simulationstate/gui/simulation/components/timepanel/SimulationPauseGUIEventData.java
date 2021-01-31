package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.timepanel;

public class SimulationPauseGUIEventData {
    private boolean isPaused;

    public SimulationPauseGUIEventData(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public boolean isPaused() {
        return isPaused;
    }

}
