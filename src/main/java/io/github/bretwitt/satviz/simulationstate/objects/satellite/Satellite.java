package io.github.bretwitt.satviz.simulationstate.objects.satellite;

import com.google.common.eventbus.Subscribe;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.ClassicalOrbitalElements;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.mathematics.astrodynamics.orbitrepresentations.SimpleTwoLineElementSet;
import io.github.bretwitt.mathematics.astrodynamics.StateVectors;
import io.github.bretwitt.satviz.simulationstate.stateevents.onupdatetleevent.UpdateTLEEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onupdatetleevent.UpdateTLEEventData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onupdateelementsevent.OnUpdateElementsData;
import io.github.bretwitt.satviz.simulationstate.gui.simulation.events.onupdateelementsevent.OnUpdateElementsEvent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.components.SatelliteAnnotationsComponent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.components.SatelliteGraphicsComponent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.components.SatelliteMotionComponent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.components.SatelliteOrbitGraphicsComponent;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.events.OnOrbitUpdateEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onupdatestatevectorevent.OnUpdateStateVectorEvent;
import io.github.bretwitt.satviz.simulationstate.stateevents.onupdatestatevectorevent.OnUpdateStateVectorEventData;

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


    @Subscribe
    public void handleStateVectorUpdate(OnUpdateStateVectorEvent event) {
        OnUpdateStateVectorEventData data = event.getUpdateData();
        Satellite satellite = data.getSatellite();

        if(satellite == this) {
            getOrbit().updateVectors(data.getStateVectors());
            getEventBus().post(new OnOrbitUpdateEvent(getOrbit()));
        }
    }

    @Subscribe
    public void handleElementsUpdate(OnUpdateElementsEvent event) {
        OnUpdateElementsData data = (OnUpdateElementsData)event.getData();
        if(data.getSatellite() == this) {
            ClassicalOrbitalElements elements = data.getElements();
            getOrbit().updateElements(elements);
            getEventBus().post(new OnOrbitUpdateEvent(getOrbit()));
        }
    }

    @Subscribe
    public void handleTLEUpdate(UpdateTLEEvent event) {
        UpdateTLEEventData data = event.getData();
        if(data.getSatellite() == this) {
            SimpleTwoLineElementSet set = data.getSet();
            getOrbit().updateTLE(set);
            getEventBus().post(new OnOrbitUpdateEvent(getOrbit()));
        }
    }

}
