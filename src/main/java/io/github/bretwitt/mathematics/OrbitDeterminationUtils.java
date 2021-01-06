package io.github.bretwitt.mathematics;

import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;

public class OrbitDeterminationUtils {

    public static Vector3f[] getOrbitPointsCartesianGeocentric(ClassicalOrbitalElements coe, int points) {
        Vector3f[] orbitPoints = getOrbitPointsCartesianPerifocal(coe, points);
        for(int i = 0; i < points; i++) {
            Matrix3f rotMatrixInclination = getYRotationMatrix(coe.getI());
            Matrix3f rotMatrixRAAN = getZRotationMatrix(coe.getRAAN());

            orbitPoints[i] = rotMatrixInclination.mult(orbitPoints[i]);
            orbitPoints[i] = rotMatrixRAAN.mult(orbitPoints[i]);
        }
        return orbitPoints;
    }

    public static Vector3f[] getOrbitPointsCartesianPerifocal(ClassicalOrbitalElements coe, int points) {
        Vector3f[] orbitPoints = new Vector3f[points];

        float step = 2 * FastMath.PI / points;

        for(int i = 0; i < points; i++) {
            float v = (step*i);
            float r = coe.getP() / (1 + (coe.getE() * FastMath.cos(v)));
            float x = r * FastMath.cos(v);
            float y = r * FastMath.sin(v);
            orbitPoints[i] = new Vector3f(x,y,0);
        }

        return orbitPoints;
    }

    public static Matrix3f getYRotationMatrix(float theta){
        return new Matrix3f(
                FastMath.cos(theta), 0, FastMath.sin(theta),
                0, 1, 0,
                -FastMath.sin(theta), 0, FastMath.cos(theta)
        );
    }

    public static Matrix3f getZRotationMatrix(float theta){
        return new Matrix3f(
              FastMath.cos(theta), -FastMath.sin(theta), 0,
              FastMath.sin(theta), FastMath.cos(theta), 0,
              0, 0, 1
        );
    }

}
