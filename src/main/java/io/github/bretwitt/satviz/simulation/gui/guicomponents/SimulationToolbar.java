package io.github.bretwitt.satviz.simulation.gui.guicomponents;

import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.satviz.simulation.gui.eventbus.GuiEventBus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public class SimulationToolbar extends GuiComponent{

    private Button addButton;
    private Button editButton;
    private Button removeButton;

    private HBox buttonBox;
    private VBox pane;

    private ListView satelliteListView;

    public SimulationToolbar(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(guiEventBus, stateEventBus, satViz);
    }

    @Override
    public void onEnable() {
        try {
            setupGUIFromFXML();
        } catch (IOException e) {
            System.out.println("Failed to load SatellitePanel GUI");
            e.printStackTrace();
        }
    }

    private void setupGUIFromFXML() throws IOException {
        URL file = getClass().getClassLoader().getResource("satellite.fxml");

        AnchorPane parent = (AnchorPane) FXMLLoader.load(Objects.requireNonNull(file));
        JavaFxUI.getInstance().attachChild(parent);

    }

    private void createListView() {
        satelliteListView = new ListView<>();
    }

}
