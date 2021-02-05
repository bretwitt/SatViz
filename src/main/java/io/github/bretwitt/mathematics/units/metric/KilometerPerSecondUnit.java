package io.github.bretwitt.mathematics.units.metric;

import io.github.bretwitt.mathematics.units.UnitConversionUtils;
import io.github.bretwitt.mathematics.units.base.speed.SpeedUnit;

public class KilometerPerSecondUnit extends SpeedUnit {

    @Override
    public float toTUDU() {
        return getValue() * UnitConversionUtils.KMStoTUDU;
    }

    @Override
    public float toTUDU(float value) {
        return value * UnitConversionUtils.KMStoTUDU;
    }

    @Override
    public float fromTUDU(float value) {
        return value * UnitConversionUtils.TUDUtoKMS;
    }
}
