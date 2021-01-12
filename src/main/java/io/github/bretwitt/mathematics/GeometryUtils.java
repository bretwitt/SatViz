package io.github.bretwitt.mathematics;

import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;

public class GeometryUtils {
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

    public static Matrix3f getXRotationMatrix(float theta) {
        return new Matrix3f(
                1, 0, 0,
                0, FastMath.cos(theta), -FastMath.sin(theta),
                0, FastMath.sin(theta), FastMath.cos(theta)
        );

    }

}
