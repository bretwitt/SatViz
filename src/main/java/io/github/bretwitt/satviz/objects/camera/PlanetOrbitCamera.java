package io.github.bretwitt.satviz.objects.camera;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import io.github.bretwitt.SatViz;
import io.github.bretwitt.engine.entities.Entity;
import io.github.bretwitt.mathematics.GeometryUtils;
import io.github.bretwitt.satviz.objects.earth.Earth;

public class PlanetOrbitCamera extends Entity implements AnalogListener {

    Earth planet;
    Vector3f position;
    Camera camera;
    float deltaZAngle;
    float deltaYAngle;

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
        camera = getSatViz().getCamera();
        getSatViz().getFlyByCamera().setEnabled(false);
        camera.setLocation(new Vector3f(-6,6,3));
    }


    @Override
    public void onEntityUpdate(float tpf) {
        updateCameraPosition();
        updateCameraRotation();
        deltaZAngle = 0;
        deltaYAngle = 0;
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
            deltaZAngle -= 2f * tpf;
        } else if (name.equals("Camera Rotate Right")) {
            deltaZAngle += 2f * tpf;
        }
        if(name.equals("Camera Rotate Up")) {
            deltaYAngle += 2f * tpf;
        } else if(name.equals("Camera Rotate Down")) {
            deltaYAngle -= 2f* tpf;
        }
    }

    public Vector3f getCameraPosition() {
        return camera.getLocation();
    }

    public void updateCameraPosition() {
        Vector3f cameraPos = getCameraPosition();
        cameraPos = turnVectorZ(cameraPos,deltaZAngle);
        cameraPos = turnVectorY(cameraPos,deltaYAngle);
        camera.setLocation(cameraPos);
    }

    public void updateCameraRotation() {
        camera.lookAt(planet.getSpatialComponent().getSpatial().getLocalTranslation(), Vector3f.UNIT_Z);
    }

    private Vector3f turnVectorX(Vector3f vector, float angle) {
        return GeometryUtils.getXRotationMatrix(angle).mult(vector);
    }

    private Vector3f turnVectorY(Vector3f vector, float angle) {
        return GeometryUtils.getYRotationMatrix(angle).mult(vector);
    }

    private Vector3f turnVectorZ(Vector3f vector, float angle) {
        return GeometryUtils.getZRotationMatrix(angle).mult(vector);
    }


}

