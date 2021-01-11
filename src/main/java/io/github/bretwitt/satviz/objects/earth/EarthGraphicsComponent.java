package io.github.bretwitt.satviz.objects.earth;

import com.google.common.eventbus.EventBus;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.SpatialComponent;

public class EarthGraphicsComponent extends SpatialComponent {
    public EarthGraphicsComponent(EventBus eventBus, SatViz app) {
        super(eventBus, app);
        Spatial earth = generateSpatial();
        updateSpatial(earth);
    }

    private Spatial generateSpatial() {
        Sphere sphere = new Sphere(30,30,1);
        Spatial s = new Geometry("Sphere",sphere);
        Material mat = new Material(getSatViz().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor( "Color", ColorRGBA.White);
        mat.setTexture("ColorMap",getSatViz().getAssetManager().loadTexture("textures/earth.jpg"));
        s.setMaterial(mat);
        return s;
    }
}
