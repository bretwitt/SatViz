package io.github.bretwitt.mathematics.astrodynamics;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import io.github.bretwitt.SatViz;

public class Orbit {

    private ClassicalOrbitalElements elements;
    private Vector3f[] orbitCoordinates;
    private int sampling = 200;

    public Orbit(ClassicalOrbitalElements classicalOrbitalElements, SatViz satViz) {
        elements = classicalOrbitalElements;
        calculateGeocentricCoordinates();
    }

    public Orbit(ClassicalOrbitalElements coe) {
        elements = coe;
        calculateGeocentricCoordinates();
    }

    public void setElements(ClassicalOrbitalElements elements) {
        this.elements = elements;
        orbitCoordinates = calculateGeocentricCoordinates();
    }

    public ClassicalOrbitalElements getOrbitalElements() {
        return elements;
    }

    public float getPeriod() {
        return OrbitGeometryUtils.getPeriod(elements.getA());
    }

    public float getSpecificAngularMomentum() {
        return OrbitGeometryUtils.getSpecificAngularMomentum(elements);
    }

    public StateVectors calculateStateVectors(float t) {
        return OrbitGeometryUtils.calculateStateVectors(elements,getPeriod(),t);
    }

    public Vector3f[] getGeocentricCoordinates() {
        if(orbitCoordinates == null) {
            orbitCoordinates = calculateGeocentricCoordinates();
        }
        return orbitCoordinates;
    }

    private Vector3f[] calculateGeocentricCoordinates() {
        return OrbitGeometryUtils.getOrbitPointsCartesianGeocentric(elements, sampling);
    }

    public float getTrueAnomalyAtTime(float tSecs) {
        return OrbitGeometryUtils.getTrueAnomalyAtTime(elements, getPeriod(), tSecs);
    }

    public Vector3f getVectorGeocentricAtTrueAnomaly(float taRadians) {
        return OrbitGeometryUtils.getVectorGeocentricAtTrueAnomaly(elements,taRadians);
    }

    public int getSample() {
        return sampling;
    }

}
