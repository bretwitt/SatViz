package io.github.bretwitt.satviz.objects.satellite;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import com.jme3.shader.VarType;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture3D;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.SpatialComponent;

public class SatelliteGraphicsComponent extends SpatialComponent {

    float v;
    Orbit orbit;

    public SatelliteGraphicsComponent(EventBus eventBus, SatViz app) {
        super(eventBus, app);
    }

    @Override
    public void onInitialize() {
        updateSpatial(generateSpatial());
        getEventBus().post(new OnSatelliteSpatialInitializedEvent(getSpatial()));
    }

    @Subscribe
    public void handleOrbitUpdateEvent(OnOrbitUpdateEvent orbitUpdateEvent) {
        orbit = (Orbit) orbitUpdateEvent.getData();
    }

    private Spatial generateSpatial() {
        Spatial sat = getSatViz().getAssetManager().loadModel("/models/MRO1/MRO1.j3o");
        sat.setLocalScale(0.1f);
        Material mat = new Material(getSatViz().getAssetManager(),  // Create new material and...
                "Common/MatDefs/Misc/Unshaded.j3md");// ... specify .j3md file to use (illuminated).
        Texture text = getSatViz().getAssetManager().loadTexture("/models/MRO1/MRO_UV.png");
        mat.setTexture("ColorMap", text);
        sat.setMaterial(mat);

        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.3f));
        rootNode.addLight(al);
        return sat;
    }

    @Override
    public void update(float tpf) {

    }
}
