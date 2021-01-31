package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.timepanel;

public class OnSimulationPauseGUIEvent {
    private SimulationPauseGUIEventData simulationPauseGUIEventData;

    public OnSimulationPauseGUIEvent(SimulationPauseGUIEventData simulationPauseGUIEventData) {
        this.simulationPauseGUIEventData = simulationPauseGUIEventData;
    }

    public SimulationPauseGUIEventData getData() {
        return simulationPauseGUIEventData;
    }
}
