package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.timepanel;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.gui.guicomponents.GuiComponent;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;

import java.net.URL;

public class TimeToolbar extends GuiComponent {
    public TimeToolbar(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(guiEventBus, stateEventBus, satViz);
    }

    @Override
    public void onEnable() {
        URL url = getClass().getClassLoader().getResource("ui/fxml/time.fxml");
        loadFromFXML(url);
    }
}
