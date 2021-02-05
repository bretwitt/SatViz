package io.github.bretwitt.mathematics.units.metric;

import io.github.bretwitt.mathematics.units.UnitConversionUtils;
import io.github.bretwitt.mathematics.units.base.time.TimeUnit;

public class MetricTimeUnit extends TimeUnit {

    public float asSeconds() {
        return getValue();
    }

    public float asMinutes() {
        return getValue() / 60;
    }

    public float asHours() {
        return getValue() / 3600;
    }

    public float asDays() {
        return getValue() / 86400;
    }

    public float asWeeks() {
        return getValue() / 604800;
    }

    @Override
    public float toTU() {
        return toTU(getValue());
    }

    @Override
    public float toTU(float value) {
        return value * UnitConversionUtils.SecToTU;
    }

    @Override
    public float fromTU(float value) {
        return value * UnitConversionUtils.TUToSec;
    }
}
