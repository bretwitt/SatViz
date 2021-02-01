package io.github.bretwitt;


import com.google.common.eventbus.EventBus;
import com.jayfella.jme.jfx.JavaFxUI;
import com.jme3.app.SimpleApplication;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;
import com.jme3.texture.Image;
import io.github.bretwitt.engine.appstates.AppState;
import io.github.bretwitt.satviz.simulationstate.SimulationState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class SatViz extends SimpleApplication {

    private AppState appState;
    private EventBus eventBus;

    public static void main(String[] args) {
        SatViz app = new SatViz();
        AppSettings settings = app.createSettings();
        app.setSettings(settings);
        initDisplaySettings(app);

        app.start();
    }
    public void simpleInitApp() {
        JavaFxUI.initialize(this);
        appState = new SimulationState();
        stateManager.attach(appState);
    }

    public static AppSettings createSettings() {
        AppSettings settings = new AppSettings(true);
        settings.setTitle("SatViz: Earth Orbit Satellite Visualization");
        settings.setResolution(1920,1080);

        try {
            settings.setIcons(new BufferedImage[]{
                    ImageIO.read(SatViz.class.getResource("/ui/graphics/appicon.png"))
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return settings;
    }

    public static void initDisplaySettings(SimpleApplication app) {
        app.setShowSettings(false);
        app.setDisplayStatView(false);
        app.setDisplayFps(false);
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

    public AppState getCurrentState() {
        return appState;
    }
}
