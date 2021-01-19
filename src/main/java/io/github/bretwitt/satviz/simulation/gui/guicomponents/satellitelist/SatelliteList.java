package io.github.bretwitt.satviz.simulation.gui.guicomponents.satellitelist;

import com.google.common.eventbus.Subscribe;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.simsilica.lemur.*;

import com.simsilica.lemur.component.BorderLayout;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.appstates.stateeventbus.StateEventBus;
import io.github.bretwitt.mathematics.astrodynamics.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.satviz.simulation.gui.eventbus.GuiEventBus;
import io.github.bretwitt.satviz.simulation.gui.guicomponents.GuiComponent;
import io.github.bretwitt.satviz.simulation.objects.satellite.Satellite;
import io.github.bretwitt.satviz.simulation.stateevents.OnSatelliteAddEvent;

public class SatelliteList extends GuiComponent {

    float resolutionX;
    float resolutionY;

    Container window;
    Container options;
    ListBox box;

    public SatelliteList(GuiEventBus guiEventBus, StateEventBus stateEventBus, SatViz satViz) {
        super(guiEventBus, stateEventBus, satViz);
    }

    @Override
    public void onInitialize() {
        window = new Container(new BorderLayout());
        getSatViz().getGuiNode().attachChild(window);

        window.setLocalTranslation(1300, 1500, 0);
        addListBox();

        options = addSatelliteOptionsPanel();
        addAddSatelliteButton();
        addEditSatelliteButton();
        addRemoveSatelliteButton();
    }

    private Container addSatelliteOptionsPanel() {
        Container satelliteOptionsPanel = new Container();
        satelliteOptionsPanel.setPreferredSize(new Vector3f(100,20,0));
        window.addChild(satelliteOptionsPanel);
        return satelliteOptionsPanel;
    }

    private void addAddSatelliteButton() {
        Button clickMe = options.addChild(new Button("+"), 0, 0);
        clickMe.addClickCommands(source -> {
            float a = (float)Math.random() * 4 + 1;
            float e = (float)Math.random() * 0.5f;
            float i = (float)Math.random() * FastMath.TWO_PI;
            float raan = (float)Math.random() * FastMath.TWO_PI;
            float tae = (float)Math.random() * FastMath.TWO_PI;
            Orbit orbit = new Orbit(new ClassicalOrbitalElements(a,e,i,raan,tae));
            Satellite satellite = new Satellite("Sat",orbit, getSatViz());
            getStateEventBus().post(new OnSatelliteAddEvent(satellite));
        });
        clickMe.setPreferredSize(new Vector3f(20,20,100));
    }

    private void addRemoveSatelliteButton() {
        Button clickMe = options.addChild(new Button("-"), 0, 1);
        clickMe.addClickCommands(new Command<Button>() {
            @Override
            public void execute( Button source ) {

            }
        });
        clickMe.setPreferredSize(new Vector3f(20,20,100));
    }

    private void addEditSatelliteButton() {
        Button clickMe = options.addChild(new Button("Edit"),0, 2);
        clickMe.addClickCommands(new Command<Button>() {
            @Override
            public void execute( Button source ) {

            }
        });
        clickMe.setPreferredSize(new Vector3f(40,20,100));
    }

    private void addListBox() {
        box = window.addChild(new ListBox(), BorderLayout.Position.North);
        box.setVisibleItems(10);
        box.setPreferredSize(new Vector3f(400,200,0));
    }

    @Subscribe
    public void handleAddSatelliteEvent(OnSatelliteAddEvent event){
        addSatellite(event.getSatellite());
    }

    private void addSatellite(Satellite satellite) {
        box.getModel().add(0,satellite.getName());
    }

}
