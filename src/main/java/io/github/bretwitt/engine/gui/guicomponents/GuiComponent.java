package io.github.bretwitt.engine.gui.guicomponents;

import com.jayfella.jme.jfx.JavaFxUI;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.gui.guicomponents.eventbus.GuiEventBus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import org.jetbrains.annotations.NotNull;

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
        Parent parent = loader.load();
        //applyJMetro(parent);
        controller = getController(loader);
        if(controller != null) {
            injectEventBus(controller);
        }
        return parent;
    }

    private void applyJMetro(Parent parent) {
        JMetro metro = new JMetro();
        metro.setParent(parent);
        metro.setStyle(Style.DARK);
    }

    @Override
    public void update(float tpf) {
        JavaFxUI.getInstance().runInJavaFxThread(()->controller.update(tpf));
    }

    public GUIController getController(@NotNull FXMLLoader loader) {
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

    public SatViz getSatViz() {
        return satViz;
    }
}
