package io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent;

public class OnAddSatelliteGUIEventData {

    private String name;
    public OnAddSatelliteGUIEventData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
