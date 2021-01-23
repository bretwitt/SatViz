package io.github.bretwitt.satviz.simulationstate.objects.satellite;

import com.google.common.eventbus.Subscribe;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.astrodynamics.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent.OnUpdateElementsData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.updatestatevectorguievent.OnUpdateElementsEvent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.components.SatelliteAnnotationsComponent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.components.SatelliteGraphicsComponent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.components.SatelliteMotionComponent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.components.SatelliteOrbitGraphicsComponent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.events.OnOrbitUpdateEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.OnUpdateStateVectorEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.OnUpdateStateVectorEventData;

import java.util.List;

public class Satellite extends Entity {

    private SatelliteGraphicsComponent graphicsComponent;
    private SatelliteMotionComponent motionComponent;
    private SatelliteOrbitGraphicsComponent orbitGraphicsComponent;
    private Orbit satOrbit;
    private String name;

    public Satellite(List<Component> components, SatViz satViz) {
        super(components, satViz);
    }

    public Satellite(String name, Orbit orbit, SatViz satViz) {
        super(satViz);
        satOrbit = orbit;
        this.name = name;
        getStateEventBus().register(this);
    }

    @Override
    public void onEntityInitialize() {
        addComponent((motionComponent = new SatelliteMotionComponent(satOrbit, getEventBus(), getSatViz())));
        addComponent((graphicsComponent = new SatelliteGraphicsComponent(satOrbit,getEventBus(),getSatViz())));
        addComponent(new SatelliteOrbitGraphicsComponent(satOrbit,getEventBus(),getSatViz()));
        addComponent(new SatelliteAnnotationsComponent(this,getEventBus(),getSatViz()));
    }

    @Subscribe
    public void handleStateVectorUpdate(OnUpdateStateVectorEvent event) {
        OnUpdateStateVectorEventData data = event.getUpdateData();
        Satellite satellite = data.getSatellite();

        if(satellite == this) {
            getOrbit().setVectors(data.getStateVectors());
            getEventBus().post(new OnOrbitUpdateEvent(getOrbit()));
        }
    }

    @Subscribe
    public void handleElementsUpdate(OnUpdateElementsEvent event) {
        OnUpdateElementsData data = (OnUpdateElementsData)event.getData();
        if(data.getSatellite() == this) {
            ClassicalOrbitalElements elements = data.getElements();
            getOrbit().setElements(elements);
            getEventBus().post(new OnOrbitUpdateEvent(getOrbit()));
        }
    }
    public StateVectors getStateVectors(float t) {
        return satOrbit.calculateStateVectors(t);
    }
    public Orbit getOrbit() {
        return satOrbit;
    }
    public String getName() {
        return name;
    }

    public Spatial getSpatial() {
        return graphicsComponent.getSpatial();
    }
    public Vector3f getPosition() {
        return motionComponent.getPosition();
    }

    public String toString() {
        return name;
    }
}