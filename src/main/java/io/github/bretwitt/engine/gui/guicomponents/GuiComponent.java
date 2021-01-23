package io.github.bretwitt.engine.gui.guicomponents;

import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.simulationtoolbar.SimulationToolbarController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class GuiComponent extends Component {

    private GuiEventBus guiEventBus;
    private StateEventBus stateEventBus;
    private SatViz satViz;
    private GUIController controller;

    public GuiComponent(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(stateEventBus,satViz);
        this.guiEventBus = guiEventBus;
        this.stateEventBus = stateEventBus;
        this.satViz = satViz;
        this.guiEventBus.register(this);
    }

    public void loadFromFXML(URL url) {
        try {
            Parent parent = loadFXML(url);
            JavaFxUI.getInstance().attachChild(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Parent loadFXML(URL file) throws IOException {
        FXMLLoader loader = new FXMLLoader(file);
        AnchorPane parent = loader.load();
        controller = getController(loader);
        injectEventBus(controller);
        return parent;
    }

    public GUIController getController(FXMLLoader loader) {
        return loader.getController();
    }

    private void injectEventBus(GUIController controller){
        controller.bind(getSatViz(),getGuiEventBus());
    }

    public GuiEventBus getGuiEventBus() {
        return guiEventBus;
    }

    public StateEventBus getStateEventBus() {
        return stateEventBus;
    }

    @Override
    public void update(float tpf) {
        if(controller != null) {
            JavaFxUI.getInstance().runInJavaFxThread(() -> controller.update());
        }
    }

    public SatViz getSatViz() {
        return satViz;
    }
}
