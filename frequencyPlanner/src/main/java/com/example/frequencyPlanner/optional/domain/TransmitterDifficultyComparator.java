package com.example.frequencyPlanner.optional.domain;

import java.io.Serializable;
import java.util.Comparator;

import com.example.frequencyPlanner.domain.Transmitter;

public class TransmitterDifficultyComparator implements Comparator<Transmitter>, Serializable {

    private static final Comparator<Transmitter> COMPARATOR = Comparator.comparingInt(Transmitter::getRequiredNumberOfFrequency)
            .thenComparingLong(Transmitter::getId);

    @Override
    public int compare(Transmitter a, Transmitter b) {
        return COMPARATOR.compare(a, b);
    }

}
