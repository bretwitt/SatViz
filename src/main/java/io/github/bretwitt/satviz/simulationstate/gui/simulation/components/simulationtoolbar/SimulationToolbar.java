package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.simulationtoolbar;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;
import io.github.bretwitt.engine.gui.guicomponents.GuiComponent;

public class SimulationToolbar extends GuiComponent {

    public SimulationToolbar(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(guiEventBus, stateEventBus, satViz);
    }

    @Override
    public void onEnable() {
        loadFromFXML(getClass().getClassLoader().getResource("interface/fx/simulationtoolbar.fxml"));
    }

}
