package io.github.bretwitt.satviz.objects.camera;

import com.jme3.input.ChaseCamera;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Spatial;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.components.Component;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.satviz.objects.earth.Earth;

import java.util.List;

public class Camera extends Entity {

    Earth earth;

    public Camera(Earth e, SatViz satViz) {
        super(satViz);
        earth = e;
    }

    public Camera(List<Component> components, SatViz satViz) {
        super(components, satViz);
    }

    @Override
    public void onEntityEnable() {
        com.jme3.renderer.Camera c = getSatViz().getCamera();
        getSatViz().getFlyByCamera().setEnabled(false);
        Spatial focus = earth.getSpatialComponent().getSpatial();
        ChaseCamera chaseCam = new ChaseCamera(c, focus, getSatViz().getInputManager());
        chaseCam.setSmoothMotion(true);

        chaseCam.setInvertVerticalAxis(true);
        //Uncomment this to invert the camera's horizontal rotation Axis
        chaseCam.setInvertHorizontalAxis(true);
        //Comment this to disable smooth camera motion

        chaseCam.setDefaultDistance(4f);
        chaseCam.setDownRotateOnCloseViewOnly(false);
        chaseCam.setDefaultVerticalRotation(FastMath.HALF_PI);

    }
}
