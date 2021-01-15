package io.github.bretwitt.mathematics.astrodynamics;

import com.jme3.math.Vector3f;

public class StateVectors {

    private Vector3f position;
    private Vector3f velocity;

    public StateVectors(Vector3f position, Vector3f velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Vector3f getVelocity() {
        return velocity;
    }

}
