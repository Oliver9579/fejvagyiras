package hu.headortail.domain.service;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Simulate {

    private final Random random;

    public Simulate(Random random) {
        this.random = random;
    }

    public String getResult() {
        return random.nextInt(2) == 0 ? "I" : "F";
    }

    public String getSequence() {
        return IntStream.range(0, 4)
                .mapToObj(i -> getResult())
                .collect(Collectors.joining());
    }
}
