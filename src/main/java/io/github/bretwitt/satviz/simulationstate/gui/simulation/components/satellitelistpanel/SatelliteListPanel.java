package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.satellitelistpanel;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.gui.guicomponents.GuiComponent;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;

public class SatelliteListPanel extends GuiComponent {
    public SatelliteListPanel(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(guiEventBus, stateEventBus, satViz);
    }

    @Override
    public void onEnable() {
        loadFromFXML(getClass().getClassLoader().getResource("interface/fx/satellitepanel.fxml"));
    }
}
