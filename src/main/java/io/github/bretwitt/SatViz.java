package io.github.bretwitt;


import com.google.common.eventbus.EventBus;
import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.satviz.OrbitViewState;


public class SatViz extends SimpleApplication {

    private AppState appState;
    private EventBus eventBus;

    public static void main(String[] args) {

        AppSettings settings = createSettings();

        SatViz app = new SatViz();
        app.setSettings(settings);
        app.setShowSettings(false);

        app.start();
    }

    public Node getRootNode() {
        return rootNode;
    }

    public EventBus getGameEventBus() {
        if(eventBus == null) {
            eventBus = new EventBus();
        }
        return eventBus;
    }

    private static AppSettings createSettings() {
        AppSettings settings = new AppSettings(true);
        settings.setTitle("SatViz: Earth Orbit Satellite Visualization");
        settings.setResolution(1280,720);
        return settings;
    }

    public void simpleInitApp() {
        appState = new OrbitViewState();
        stateManager.attach(appState);
    }

    public void simpleUpdate(float tpf) {
    }
}
