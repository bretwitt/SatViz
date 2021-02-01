package io.github.bretwitt.satviz.simulationstate.objects.satellite.components;

import com.google.common.eventbus.EventBus;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.SpatialComponent;
import io.github.bretwitt.mathematics.astrodynamics.Orbit;
import io.github.bretwitt.satviz.simulationstate.objects.satellite.events.OnSatelliteSpatialInitializedEvent;

public class SatelliteGraphicsComponent extends SpatialComponent {

    Orbit orbit;

    public SatelliteGraphicsComponent(Orbit orbit, EventBus eventBus, SatViz app) {
        super(eventBus, app);
        this.orbit = orbit;
    }

    @Override
    public void onInitialize() {
        updateSpatial(generateSpatial());
        getEventBus().post(new OnSatelliteSpatialInitializedEvent(getSpatial()));
    }

    private Spatial generateSpatial() {
        Spatial sat = getSatViz().getAssetManager().loadModel("/3d/MRO1/MRO1.j3o");
        sat.setLocalScale(0.1f);
        Material mat = new Material(getSatViz().getAssetManager(),  // Create new material and...
                "Common/MatDefs/Misc/Unshaded.j3md");// ... specify .j3md file to use (illuminated).
        Texture text = getSatViz().getAssetManager().loadTexture("/3d/MRO1/MRO_UV.png");
        mat.setTexture("ColorMap", text);
        sat.setMaterial(mat);

        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.3f));
        rootNode.addLight(al);
        return sat;
    }

}
