package io.github.bretwitt.satviz.simulationstate.objects.camera;

import com.jme3.input.ChaseCamera;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.CameraNode;
import com.jme3.scene.Node;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.GeometryUtils;
import io.github.bretwitt.satviz.simulationstate.objects.earth.Earth;

import javax.swing.event.MouseInputListener;
import java.util.Vector;

public class PlanetOrbitCamera extends Entity implements AnalogListener {

    Earth planet;
    Camera camera;
    float pitch;
    float yaw;
    float zoom;

    public PlanetOrbitCamera(Earth e, SatViz satViz) {
        super(satViz);
        planet = e;
    }

    @Override
    public void onEntityEnable() {
        registerInput();
        setupCamera();
    }

    private void setupCamera() {
        getSatViz().getFlyByCamera().setEnabled(false);
        camera = getSatViz().getCamera();

        camera.setLocation(new Vector3f(20,0,0));
    }


    @Override
    public void onEntityUpdate(float tpf) {
        updateCameraPosition();
        updateCameraRotation();
        pitch = 0;
        yaw = 0;
    }


    public void registerInput() {

        InputManager inputManager = getSatViz().getInputManager();

        inputManager.addMapping("Camera Rotate Left", new KeyTrigger(KeyInput.KEY_A));
        inputManager.addMapping("Camera Rotate Right", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("Camera Rotate Up", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Camera Rotate Down", new KeyTrigger(KeyInput.KEY_S));

        inputManager.addListener(this, "Camera Rotate Left");
        inputManager.addListener(this, "Camera Rotate Right");
        inputManager.addListener(this, "Camera Rotate Up");
        inputManager.addListener(this, "Camera Rotate Down");

    }

    @Override
    public void onAnalog(String name, float v, float tpf) {
        if(name.equals("Camera Rotate Left")) {
            yaw -= 2f * tpf;
        } else if (name.equals("Camera Rotate Right")) {
            yaw += 2f * tpf;
        }
        if(name.equals("Camera Rotate Up")) {
            pitch += 2f * tpf;
        } else if(name.equals("Camera Rotate Down")) {
            pitch -= 2f* tpf;
        }
    }

    public Vector3f getCameraPosition() {
        return camera.getLocation();
    }

    public void updateCameraPosition() {
        Vector3f cameraPosition = rotateCamera(pitch,yaw,camera.getLocation());
        camera.setLocation(cameraPosition);
    }

    public void updateCameraRotation() {
        camera.lookAt(planet.getSpatialComponent().getSpatial().getLocalTranslation(), Vector3f.UNIT_Z);
    }

    private Vector3f rotateCamera(float pitch, float yaw, Vector3f original) {
        return pitchYawRotMatrix(pitch,yaw).mult(original);
    }

    private Matrix3f pitchYawRotMatrix(float pitch, float yaw) {
        return GeometryUtils.getYRotationMatrix(pitch).mult(GeometryUtils.getZRotationMatrix(yaw));
    }


}

