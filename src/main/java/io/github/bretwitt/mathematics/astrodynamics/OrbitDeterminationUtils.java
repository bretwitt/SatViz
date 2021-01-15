package io.github.bretwitt.mathematics.astrodynamics;

import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import io.github.bretwitt.mathematics.GeometryUtils;

public class OrbitDeterminationUtils {

    public static Vector3f[] getOrbitPointsCartesianGeocentric(ClassicalOrbitalElements coe, int points) {
        Vector3f[] orbitPoints = getOrbitPointsCartesianPerifocal(coe, points);
        for(int i = 0; i < points; i++) {
            Matrix3f rotMatrixInclination = GeometryUtils.getXRotationMatrix(coe.getI());
            Matrix3f rotMatrixRAAN = GeometryUtils.getZRotationMatrix(coe.getRAAN());

            orbitPoints[i] = rotMatrixInclination.mult(orbitPoints[i]);
            orbitPoints[i] = rotMatrixRAAN.mult(orbitPoints[i]);
        }
        return orbitPoints;
    }

    public static Vector3f[] getOrbitPointsCartesianPerifocal(ClassicalOrbitalElements coe, int points) {
        Vector3f[] orbitPoints = new Vector3f[points];

        float step = FastMath.TWO_PI / points;

        for(int i = 0; i < points; i++) {
            float v = (step*i);
            float r = coe.getP() / (1 + (coe.getE() * FastMath.cos(v)));
            float x = r * FastMath.cos(v);
            float y = r * FastMath.sin(v);
            orbitPoints[i] = new Vector3f(x,y,0);
        }

        return orbitPoints;
    }

}
