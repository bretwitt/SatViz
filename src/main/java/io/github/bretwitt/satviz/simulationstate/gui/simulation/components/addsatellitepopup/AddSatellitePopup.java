package io.github.bretwitt.satviz.simulationstate.gui.simulation.components.addsatellitepopup;

import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.gui.guicomponents.GuiComponent;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;

import java.net.URL;

public class AddSatellitePopup extends GuiComponent {
    public AddSatellitePopup(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(guiEventBus, stateEventBus, satViz);
    }

    @Override
    public void onEnable() {
        URL url = getClass().getClassLoader().getResource("ui/fxml/addsatellitepopup.fxml");
        loadFromFXML(url);
    }

    @Override
    public void update(float tpf) {
    }
}
