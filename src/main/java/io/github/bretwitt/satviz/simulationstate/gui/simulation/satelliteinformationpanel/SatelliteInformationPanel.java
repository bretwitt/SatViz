package io.github.bretwitt.satviz.simulationstate.gui.simulation.satelliteinformationpanel;

import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.gui.guicomponents.GuiComponent;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

public class SatelliteInformationPanel extends GuiComponent {

    public SatelliteInformationPanel(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(guiEventBus, stateEventBus, satViz);
    }

    @Override
    public void onEnable() {
        loadFromFXML(getClass().getClassLoader().getResource("interface/satelliteinformationpanel.fxml"));
    }

}
