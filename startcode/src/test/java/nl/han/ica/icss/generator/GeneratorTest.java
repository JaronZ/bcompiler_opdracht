package nl.han.ica.icss.generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratorTest {
    private Generator sut;

    @BeforeEach
    void setup() {
        sut = new Generator();
    }

    @Test
    void testLevel2() {
        String exp = Fixtures.generatedLevel2();
        String act = sut.generate(Fixtures.transformedLevel2());
        assertEquals(exp, act);
    }

    @Test
    void testLevel3() {
        String exp = Fixtures.generatedLevel3();
        String act = sut.generate(Fixtures.transformedLevel3());
        assertEquals(exp, act);
    }
}
